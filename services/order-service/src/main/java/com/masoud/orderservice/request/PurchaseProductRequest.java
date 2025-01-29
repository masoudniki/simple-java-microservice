package com.masoud.orderservice.request;

public record PurchaseProductRequest(
        Integer customerId,
        Integer productId
) {
}
