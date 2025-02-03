package com.masoud.orderservice.response;

import com.masoud.orderservice.enums.PaymentMethods;

import java.time.LocalDateTime;

public record CreatePaymentResponse(
        Integer paymentId,
        LocalDateTime createdAt,
        String customerId,
        PaymentMethods paymentMethod,
        Integer orderId
) {
}
