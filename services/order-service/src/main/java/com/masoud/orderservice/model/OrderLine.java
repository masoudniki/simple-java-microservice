package com.masoud.orderservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class OrderLine {

    @Id
    private Integer id;
    private Integer productId;
    private BigDecimal salePrice;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
