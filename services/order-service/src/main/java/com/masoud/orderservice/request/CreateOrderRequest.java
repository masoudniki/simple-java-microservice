package com.masoud.orderservice.request;

import com.masoud.orderservice.enums.PaymentMethods;

import java.util.List;

public record CreateOrderRequest(
        Integer customerId,
        PaymentMethods paymentMethod,
        List<Integer> products
){
}
