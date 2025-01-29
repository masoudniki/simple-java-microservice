package com.masoud.paymentservice.controller;

import com.masoud.paymentservice.request.CreatePaymentRequest;
import com.masoud.paymentservice.response.CreatePaymentResponse;
import com.masoud.paymentservice.response.PaymentResponse;
import com.masoud.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    ResponseEntity<CreatePaymentResponse> createPayment(@RequestBody CreatePaymentRequest request) {
        return ResponseEntity.ok(paymentService.createPayment(request));
    }


    @GetMapping("/{paymentId}")
    ResponseEntity<PaymentResponse> getResponseById(@PathVariable Integer paymentId) {
        return ResponseEntity.ok(paymentService.getPaymentById(paymentId));
    }

}
