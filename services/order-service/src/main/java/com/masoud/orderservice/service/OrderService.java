package com.masoud.orderservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.masoud.orderservice.clients.CustomerClient;
import com.masoud.orderservice.clients.PaymentClient;
import com.masoud.orderservice.clients.ProductClient;
import com.masoud.orderservice.events.KafkaOrderCreated;
import com.masoud.orderservice.exceptions.BusinessException;
import com.masoud.orderservice.mappers.OrderMapper;
import com.masoud.orderservice.model.Order;
import com.masoud.orderservice.model.OrderLine;
import com.masoud.orderservice.repositories.OrderLineRepository;
import com.masoud.orderservice.repositories.OrderRepository;
import com.masoud.orderservice.request.CreateOrderRequest;
import com.masoud.orderservice.request.CreatePaymentRequest;
import com.masoud.orderservice.request.PurchaseProductRequest;
import com.masoud.orderservice.response.CreatePaymentResponse;
import com.masoud.orderservice.response.CustomerResponse;
import com.masoud.orderservice.response.OrderResponse;
import com.masoud.orderservice.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final PaymentClient paymentClient;
    private final KafkaTemplate<String,KafkaOrderCreated> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final OrderMapper orderMapper;
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
            productClient.purchaseProduct(
                    new PurchaseProductRequest(request.customerId(),productId)
            );
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
            SendResult<String, KafkaOrderCreated> result =  kafkaTemplate.send("order",new KafkaOrderCreated(
                    request.customerId(),
                    order.getId(),
                    payment.paymentId(),
                    request.paymentMethod(),
                    totalPrice,
                    order.getCreatedAt()
            )).get();

            System.out.println("Message sent: " + result.getRecordMetadata());
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }

    }
    public List<OrderResponse> getOrders() {
        return orderRepository.findAll().stream().map(
            orderMapper::toOrderResponse
        ).toList();
    }

    public OrderResponse getOrder(Integer orderId) {
        return orderRepository.findById(orderId).map(orderMapper::toOrderResponse)
                .orElseThrow( () -> new BusinessException("Order not found"));
    }
    public void deleteOrder(Integer orderId) {
        orderRepository.deleteById(orderId);
    }



}
