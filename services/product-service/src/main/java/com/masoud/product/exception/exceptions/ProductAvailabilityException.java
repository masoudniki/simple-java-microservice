package com.masoud.product.exception.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductAvailabilityException extends RuntimeException {
    private final String msg;
}
