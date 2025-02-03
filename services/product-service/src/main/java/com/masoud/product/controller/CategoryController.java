package com.masoud.product.controller;

import com.masoud.product.request.CategoryCreateRequest;
import com.masoud.product.request.CategoryUpdateRequest;
import com.masoud.product.request.ProductCreateRequest;
import com.masoud.product.request.ProductUpdateRequest;
import com.masoud.product.response.CategoryResponse;
import com.masoud.product.response.ProductResponse;
import com.masoud.product.service.CategoryService;
import com.masoud.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;


    @PostMapping
    public ResponseEntity<Integer> createCategory(@RequestBody @Valid CategoryCreateRequest request) {
        return ResponseEntity.ok(categoryService.createCategory(request));
    }
    @PutMapping("/{categoryId}")
    public ResponseEntity<?> updateCategory(@PathVariable Integer categoryId,@RequestBody @Valid CategoryUpdateRequest request) {
        categoryService.updateCategory(categoryId, request);
        return ResponseEntity.accepted().build();
    }
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.accepted().build();
    }
    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getCategoryById(@PathVariable Integer categoryId) {
        return ResponseEntity.ok(categoryService.getCategory(categoryId));
    }
    @GetMapping()
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
}
