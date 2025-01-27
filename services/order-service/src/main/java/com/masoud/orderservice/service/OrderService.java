package com.masoud.orderservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.masoud.orderservice.clients.CustomerClient;
import com.masoud.orderservice.clients.PaymentClient;
import com.masoud.orderservice.clients.ProductClient;
import com.masoud.orderservice.events.KafkaOrderCreated;
import com.masoud.orderservice.exceptions.BusinessException;
import com.masoud.orderservice.model.Order;
import com.masoud.orderservice.model.OrderLine;
import com.masoud.orderservice.repositories.OrderLineRepository;
import com.masoud.orderservice.repositories.OrderRepository;
import com.masoud.orderservice.request.CreateOrderRequest;
import com.masoud.orderservice.request.CreatePaymentRequest;
import com.masoud.orderservice.response.CreatePaymentResponse;
import com.masoud.orderservice.response.CustomerResponse;
import com.masoud.orderservice.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final PaymentClient paymentClient;
    private final KafkaTemplate<String,String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    public void createOrder(CreateOrderRequest request) {
        // get the customer detail
        CustomerResponse customerResponse = customerClient.getCustomer(request.customerId())
                .orElseThrow( () -> new BusinessException("Customer not found"));

        // get the product details
        HashMap<Integer,ProductResponse> productResponses = new HashMap<>();
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Integer productId : request.products()){
            ProductResponse productResponse = productClient.getProduct(productId);
            productResponses.put(productId, productResponse);
            totalPrice = totalPrice.add(productResponse.price());
        }
        // purchase the product
        for (Integer productId : request.products()){
            productClient.purchaseProduct(productId);
        }
        // submit order
        Order order = Order.builder()
                .customerId(request.customerId())
                .build();
        orderRepository.save(order);
        // submit order line
        for (Integer productId : request.products()){
            OrderLine orderLine = OrderLine.builder()
                    .productId(productId)
                    .salePrice(productResponses.get(productId).price())
                    .order(order)
                    .build();

            orderLineRepository.save(orderLine);
        }
        // submit payment
        CreatePaymentResponse payment = paymentClient.createPayment(new CreatePaymentRequest(
                order.getId(),
                request.customerId(),
                request.paymentMethod(),
                totalPrice
        ));
        // produce payment in kafka
        try
        {
            kafkaTemplate.send("order",objectMapper.writeValueAsString(            new KafkaOrderCreated(
                    request.customerId(),
                    order.getId(),
                    payment.paymentId(),
                    request.paymentMethod(),
                    totalPrice,
                    order.getCreatedAt()
            )));
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }

    }
}
