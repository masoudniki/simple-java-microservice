package com.masoud.orderservice.response;

public record CustomerResponse(
        Integer id,
        String firstName,
        String lastName,
        String email
) {
}
