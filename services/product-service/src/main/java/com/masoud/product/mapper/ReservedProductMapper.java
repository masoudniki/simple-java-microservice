package com.masoud.product.mapper;

import com.masoud.product.model.ReservedProduct;
import com.masoud.product.response.ReservedProductResponse;
import org.springframework.stereotype.Service;

@Service
public class ReservedProductMapper {
    public ReservedProductResponse toReservedProductResponse(ReservedProduct reservedProduct) {
        return new ReservedProductResponse(
                reservedProduct.getCustomerId(),
                reservedProduct.getProduct().getId(),
                reservedProduct.getCreatedAt()
        );
    }
}
