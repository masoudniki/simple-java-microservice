package com.masoud.product.mapper;

import com.masoud.product.model.Category;
import com.masoud.product.model.Product;
import com.masoud.product.request.ProductCreateRequest;
import com.masoud.product.response.CategoryResponse;
import com.masoud.product.response.ProductResponse;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getAvailabilityQuantity(),
                Hibernate.isInitialized(product.getCategory()) ?
                        new CategoryResponse(product.getId(), product.getCategory().getName(),product.getCategory().getDescription()) :
                        null
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
