package com.masoud.product.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;


public record ProductUpdateRequest(
        String name,
        String description,
        BigDecimal price,
        double availabilityQuantity,
        Integer categoryId
) {
}
