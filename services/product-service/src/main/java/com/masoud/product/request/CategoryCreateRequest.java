package com.masoud.product.request;

import jakarta.validation.constraints.NotNull;

public record CategoryCreateRequest(
        @NotNull
        String name,
        @NotNull
        String description
) {
}
