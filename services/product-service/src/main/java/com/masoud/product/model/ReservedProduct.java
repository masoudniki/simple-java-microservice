package com.masoud.product.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class ReservedProduct {

    @Id
    private Integer Id;
    private LocalDateTime createdAt;
    private LocalDateTime reservedUntil;
    private Integer customerId;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
