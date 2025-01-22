package com.masoud.product.repository;

import com.masoud.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;


public interface ProductRepository extends JpaRepository<Product,Integer> {

}
