package com.masoud.orderservice.response;

import java.time.LocalDateTime;

public record OrderResponse(
        Integer id,
        String customerId,
        LocalDateTime createdAt
){
}
