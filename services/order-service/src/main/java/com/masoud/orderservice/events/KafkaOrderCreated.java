package com.masoud.orderservice.events;

import com.masoud.orderservice.enums.PaymentMethods;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record KafkaOrderCreated(
        Integer customerId,
        Integer orderId,
        Integer paymentId,
        PaymentMethods paymentMethod,
        BigDecimal price,
        LocalDateTime createdAt
){
}
