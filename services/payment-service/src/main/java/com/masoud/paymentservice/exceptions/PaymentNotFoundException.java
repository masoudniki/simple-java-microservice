package com.masoud.paymentservice.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PaymentNotFoundException extends RuntimeException {
    private final String msg;
}
