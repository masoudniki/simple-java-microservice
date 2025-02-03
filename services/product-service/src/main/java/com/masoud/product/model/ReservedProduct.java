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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reserved_product_seq")
    @SequenceGenerator(name = "reserved_product_seq", sequenceName = "reserved_product_seq", allocationSize = 1)
    private Integer Id;
    private LocalDateTime createdAt;
    private LocalDateTime reservedUntil;
    private String customerId;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
