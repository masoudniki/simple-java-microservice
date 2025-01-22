package com.masoud.product.repository;

import com.masoud.product.model.Category;
import com.masoud.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category,Integer> {

}
