package com.masoud.customerservice.customer;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
         String id,
         @NotNull(message="customer first name is required")
         String firstName,
         @NotNull(message="customer lastName is required")
         String lastName,
         @NotNull(message="customer email is required")
         @Email()
         String email,
         @NotNull
         Address address
) {
}
