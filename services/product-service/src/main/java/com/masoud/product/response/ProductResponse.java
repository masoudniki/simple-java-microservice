package com.masoud.product.response;

import com.masoud.product.model.Category;

import java.math.BigDecimal;

public record ProductResponse(
        Integer id,
        String name,
        String description,
        BigDecimal price,
        Double availabilityQuantity,
        CategoryResponse category
) {
}
