package com.masoud.orderservice.service;

import com.masoud.orderservice.clients.CustomerClient;
import com.masoud.orderservice.clients.ProductClient;
import com.masoud.orderservice.exceptions.BusinessException;
import com.masoud.orderservice.repositories.OrderRepository;
import com.masoud.orderservice.request.CreateOrderRequest;
import com.masoud.orderservice.response.CustomerResponse;
import com.masoud.orderservice.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    public void createOrder(CreateOrderRequest request) {
        // get the customer detail
        CustomerResponse customerResponse = customerClient.getCustomer(request.customerId())
                .orElseThrow( () -> new BusinessException("Customer not found"));

        // get the product details
        List<ProductResponse> productResponses = new ArrayList<>();

        for (Integer productId : request.products()){
            productResponses.add(
                    productClient.getProduct(productId).orElseThrow(() -> new BusinessException("product not found"))
            );
        }
        // purchase the product
        for (ProductResponse productResponse : productResponses){

        }


        // submit order

        // submit order line

        // submit payment

        // produce confirm payment in kafka
    }
}
