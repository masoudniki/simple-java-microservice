package com.masoud.orderservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class OrderLine {

    @Id
    private Integer id;
    private Integer productId;
    private BigDecimal salePrice;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
