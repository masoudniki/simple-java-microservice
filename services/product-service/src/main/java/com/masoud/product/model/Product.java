package com.masoud.product.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Product {

    @Id
    @GeneratedValue()
    private Integer id;

    private String name;
    private String description;
    private BigDecimal price;
    @Column(name = "available_quantity")
    private Double availabilityQuantity=10D;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;
}
