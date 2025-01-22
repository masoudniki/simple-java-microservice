package com.masoud.customerservice.exceptions.responses;

import java.util.HashMap;

public record ErrorResponse(
        HashMap<String,String> errors
) {
}
