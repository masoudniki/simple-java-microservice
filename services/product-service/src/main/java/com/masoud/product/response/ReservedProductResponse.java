package com.masoud.product.response;

import java.time.LocalDateTime;

public record ReservedProductResponse(
        Integer customerId,
        Integer productId,
        LocalDateTime reservedUntil
) {
}
