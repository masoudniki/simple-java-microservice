package com.masoud.paymentservice.request;

import com.masoud.paymentservice.enums.PaymentMethods;

import java.math.BigDecimal;

public record CreatePaymentRequest(
        String customerId,
        Integer orderId,
        BigDecimal price,
        PaymentMethods paymentMethod
){
}
