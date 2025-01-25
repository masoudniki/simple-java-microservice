package com.masoud.product.mapper;

import com.masoud.product.model.Category;
import com.masoud.product.response.CategoryResponse;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {
    public CategoryResponse toCategoryResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }

}
