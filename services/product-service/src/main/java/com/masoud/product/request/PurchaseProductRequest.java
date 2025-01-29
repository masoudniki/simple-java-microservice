package com.masoud.product.request;

public record PurchaseProductRequest(
        Integer customerId,
        Integer productId
) {
}
