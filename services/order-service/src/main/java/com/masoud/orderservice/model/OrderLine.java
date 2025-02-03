package com.masoud.orderservice.model;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_line_id_seq")
    @SequenceGenerator(name = "order_line_id_seq", sequenceName = "order_line_id_seq", allocationSize = 1)
    private Integer id;
    private Integer productId;
    private BigDecimal salePrice;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
