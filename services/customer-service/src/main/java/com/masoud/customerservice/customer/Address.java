package com.masoud.customerservice.customer;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Validated
public class Address {
    @NotNull
    private String street;
    @NotNull
    private String houseNumber;
    @NotNull
    private String zipCode;
}
