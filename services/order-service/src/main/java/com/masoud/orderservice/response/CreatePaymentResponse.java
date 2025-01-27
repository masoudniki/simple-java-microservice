package com.masoud.orderservice.response;

import com.masoud.orderservice.enums.PaymentMethods;

import java.time.LocalDateTime;

public record CreatePaymentResponse(
        Integer paymentId,
        LocalDateTime createdAt,
        Integer customerId,
        PaymentMethods paymentMethod,
        Integer orderId
) {
}
