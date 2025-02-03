package com.masoud.product.response;

import java.time.LocalDateTime;

public record ReservedProductResponse(
        String customerId,
        Integer productId,
        LocalDateTime reservedUntil
) {
}
