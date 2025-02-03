package com.masoud.orderservice.request;

import com.masoud.orderservice.enums.PaymentMethods;

import java.math.BigDecimal;

public record CreatePaymentRequest(
        Integer orderId,
        String customerId,
        PaymentMethods paymentMethod,
        BigDecimal price
) {
}
