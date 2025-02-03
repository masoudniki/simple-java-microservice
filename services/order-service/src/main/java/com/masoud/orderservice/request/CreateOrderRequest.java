package com.masoud.orderservice.request;

import com.masoud.orderservice.enums.PaymentMethods;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateOrderRequest(
        @NotNull
        String customerId,
        @NotNull
        PaymentMethods paymentMethod,
        @NotNull
        List<Integer> products
){
}
