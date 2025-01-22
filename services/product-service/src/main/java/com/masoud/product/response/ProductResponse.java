package com.masoud.product.response;

import com.masoud.product.model.Category;

import java.math.BigDecimal;

public record ProductResponse(
        String name,
        String description,
        BigDecimal price,
        double availabilityQuantity,
        Category category
) {
}
