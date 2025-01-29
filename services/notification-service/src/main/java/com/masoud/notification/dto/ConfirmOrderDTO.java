package com.masoud.notification.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ConfirmOrderDTO(
        Integer customerId,
        Integer orderId,
        Integer paymentId,
        String paymentMethod,
        BigDecimal price,
        LocalDateTime createdAt
) {
}
