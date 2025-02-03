package com.masoud.product.request;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;


public record ProductCreateRequest(
        @NotNull
        String name,
        String description,
        @NotNull
        BigDecimal price,
        @NotNull
        Double availableQuantity,
        @NotNull
        Integer categoryId
) {
}
