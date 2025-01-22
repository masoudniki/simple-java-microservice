package com.masoud.product.response;

import com.masoud.product.model.Category;

import java.math.BigDecimal;

public record CategoryResponse(
        Integer id,
        String name,
        String description
) {
}
