package com.masoud.product.request;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;


public record ProductCreateRequest(
        @NotNull
        String name,
        String description,
        @NotNull
        BigDecimal price,
        @NotNull
        double availableQuantity,
        @NotNull
        Integer categoryId
) {
}
