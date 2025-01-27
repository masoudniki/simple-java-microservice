package com.masoud.orderservice.request;

import com.masoud.orderservice.enums.PaymentMethods;

import java.math.BigDecimal;

public record CreatePaymentRequest(
        Integer orderId,
        Integer customerId,
        PaymentMethods paymentMethod,
        BigDecimal price
) {
}
