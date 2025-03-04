package com.masoud.paymentservice.response;

import com.masoud.paymentservice.enums.PaymentMethods;
import com.masoud.paymentservice.enums.PaymentStatus;

public record PaymentResponse(
        Integer id,
        Integer orderId,
        String customerId,
        PaymentMethods paymentMethod,
        PaymentStatus paymentStatus
){
}
