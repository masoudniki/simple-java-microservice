package com.masoud.product.repository;

import com.masoud.product.model.ReservedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservedProductRepository extends JpaRepository<ReservedProduct,Integer> {
}
