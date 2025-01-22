package com.masoud.product.service;

import ch.qos.logback.core.util.StringUtil;
import com.masoud.product.exception.exceptions.CategoryNotFoundException;
import com.masoud.product.mapper.CategoryMapper;
import com.masoud.product.model.Category;
import com.masoud.product.repository.CategoryRepository;
import com.masoud.product.request.CategoryCreateRequest;
import com.masoud.product.request.CategoryUpdateRequest;
import com.masoud.product.response.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public Integer createCategory(CategoryCreateRequest request) {
        var product = Category.builder()
                .name(request.name())
                .description(request.description())
                        .build();

        return categoryRepository.save(product).getId();
    }
    public Category updateCategory(Integer categoryId,CategoryUpdateRequest request){
        var product = categoryRepository.findById(categoryId).orElseThrow(()-> new CategoryNotFoundException("there is not any product with given information."));

        mergeCategory(request,product);

        return categoryRepository.save(product);
    }

    public Category getCategory(Integer productId){
        return categoryRepository.findById(productId).orElseThrow(()-> new CategoryNotFoundException("there is not any product with given information."));
    }
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll().stream().map(categoryMapper::toCategoryResponse).toList();
    }


    private void mergeCategory(CategoryUpdateRequest request, Category product){
        if(StringUtil.notNullNorEmpty(request.name())){
            product.setName(request.name());
        }
        if(StringUtil.notNullNorEmpty(request.description())){
            product.setDescription(request.description());
        }
    }


    public void deleteCategory(Integer categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
