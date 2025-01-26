package com.masoud.orderservice.model;

import com.masoud.orderservice.enums.PaymentMethods;
import com.masoud.orderservice.enums.PaymentStatus;
import jakarta.persistence.*;
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
    @Enumerated(EnumType.STRING)
    private PaymentMethods paymentMethod;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
