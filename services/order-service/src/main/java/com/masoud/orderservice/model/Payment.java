package com.masoud.orderservice.model;

import com.masoud.orderservice.enums.PaymentMethods;
import com.masoud.orderservice.enums.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Payment {
    @Id
    private Integer id;
    private PaymentMethods paymentMethod;
    private PaymentStatus paymentStatus;
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
