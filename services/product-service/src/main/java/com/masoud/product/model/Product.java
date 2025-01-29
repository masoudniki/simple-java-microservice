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
    @Column(name = "availability_quantity")
    private Double availabilityQuantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, optional = false)
    private ReservedProduct reservedProduct;
}
