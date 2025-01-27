package com.masoud.paymentservice.service;

import com.masoud.paymentservice.exceptions.PaymentNotFoundException;
import com.masoud.paymentservice.model.Payment;
import com.masoud.paymentservice.repository.PaymentRepository;
import com.masoud.paymentservice.request.CreatePaymentRequest;
import com.masoud.paymentservice.response.CreatePaymentResponse;
import com.masoud.paymentservice.response.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public CreatePaymentResponse  createPayment(CreatePaymentRequest request) {

        Payment payment = Payment.builder()
                .paymentMethod(request.paymentMethod())
                .orderId(request.orderId())
                .amount(request.price())
                .customerId(request.customerId())
                .build();

        paymentRepository.save(payment);

        return new CreatePaymentResponse(
            payment.getId(),
                payment.getCustomerId(),
                payment.getOrderId(),
                payment.getAmount(),
                payment.getPaymentMethod(),
                payment.getPaymentStatus()
        );

    }
    public PaymentResponse getPaymentById(Integer paymentId) {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(
                () -> {
                    throw new PaymentNotFoundException("payment not found");
                }
        );


        return new PaymentResponse(
            payment.getId(),
                payment.getOrderId(),
                payment.getCustomerId(),
                payment.getPaymentMethod(),
                payment.getPaymentStatus()
        );
    }
}
