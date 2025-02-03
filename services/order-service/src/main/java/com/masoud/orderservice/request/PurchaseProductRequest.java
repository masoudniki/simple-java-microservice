package com.masoud.orderservice.request;

public record PurchaseProductRequest(
        String customerId,
        Integer productId
) {
}
