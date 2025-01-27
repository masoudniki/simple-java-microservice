package com.masoud.paymentservice.response;

import com.masoud.paymentservice.enums.PaymentMethods;
import com.masoud.paymentservice.enums.PaymentStatus;

import java.math.BigDecimal;

public record CreatePaymentResponse(
        Integer id,
        Integer customerId,
        Integer orderId,
        BigDecimal price,
        PaymentMethods paymentMethod,
        PaymentStatus paymentStatus
){
}
