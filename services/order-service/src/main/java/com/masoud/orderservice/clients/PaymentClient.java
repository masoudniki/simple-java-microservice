package com.masoud.orderservice.clients;

import com.masoud.orderservice.request.CreatePaymentRequest;
import com.masoud.orderservice.response.CreatePaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "payment-service",
        url = "${application.config.payment-url}"
)
public interface PaymentClient {
    @PostMapping
    CreatePaymentResponse createPayment(@RequestBody CreatePaymentRequest request);
}
