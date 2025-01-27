package com.masoud.paymentservice.model;

import com.masoud.paymentservice.enums.PaymentMethods;
import com.masoud.paymentservice.enums.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
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
    Integer id;
    BigDecimal amount;
    Integer customerId;
    Integer orderId;
    @Enumerated(EnumType.STRING)
    PaymentMethods paymentMethod;
    @Enumerated(EnumType.STRING)
    PaymentStatus paymentStatus;
    LocalDateTime createdAt;
}
