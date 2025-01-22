package com.masoud.product.mapper;

import com.masoud.product.model.Category;
import com.masoud.product.model.Product;
import com.masoud.product.request.ProductCreateRequest;
import com.masoud.product.response.ProductResponse;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getAvailabilityQuantity(),
                product.getCategory()
        );
    }

    public Product toProduct(ProductCreateRequest productCreateRequest) {
        return Product.builder()
                .name(productCreateRequest.name())
                .description(productCreateRequest.description())
                .price(productCreateRequest.price())
                .availabilityQuantity(productCreateRequest.availableQuantity())
                .build();
    }
}
