package com.masoud.orderservice.response;

import org.springframework.cloud.openfeign.FeignClient;

import java.math.BigDecimal;


public record ProductResponse(
        Integer id,
        String name,
        String description,
        BigDecimal price,
        Double availabilityQuantity
){
}
