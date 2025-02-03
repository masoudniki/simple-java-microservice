package com.masoud.product.request;

public record PurchaseProductRequest(
        String customerId,
        Integer productId
) {
}
