package com.masoud.product.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Builder
public class Category {
    @Id
    @GeneratedValue()
    private Integer id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "category",cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    private List<Product> products;
}
