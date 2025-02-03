package com.masoud.paymentservice.model;

import com.masoud.paymentservice.enums.PaymentMethods;
import com.masoud.paymentservice.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_id_seq")
    @SequenceGenerator(name = "payment_id_seq", sequenceName = "payment_id_seq", allocationSize = 1)
    Integer id;
    BigDecimal amount;
    String customerId;
    Integer orderId;
    @Enumerated(EnumType.STRING)
    PaymentMethods paymentMethod;
    @Enumerated(EnumType.STRING)
    PaymentStatus paymentStatus;
    LocalDateTime createdAt;
}
