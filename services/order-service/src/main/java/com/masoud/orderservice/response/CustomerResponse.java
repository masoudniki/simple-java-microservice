package com.masoud.orderservice.response;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
