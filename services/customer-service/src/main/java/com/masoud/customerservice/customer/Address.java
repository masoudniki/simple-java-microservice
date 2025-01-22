package com.masoud.customerservice.customer;

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
    private String street;
    private String houseNumber;
    private String zipCode;
}
