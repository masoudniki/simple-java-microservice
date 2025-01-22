package com.masoud.product.controller;

import com.masoud.product.model.Product;
import com.masoud.product.request.ProductCreateRequest;
import com.masoud.product.request.ProductUpdateRequest;
import com.masoud.product.response.ProductResponse;
import com.masoud.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;


    @PostMapping
    public ResponseEntity<Integer> createProduct(@RequestBody @Valid ProductCreateRequest request) {
        return ResponseEntity.ok(productService.createProduct(request));
    }
    @PutMapping("/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable Integer productId,@RequestBody @Valid ProductUpdateRequest request) {
        productService.updateProduct(productId, request);
        return ResponseEntity.accepted().build();
    }
    @DeleteMapping
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.accepted().build();
    }
    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable Integer productId) {
        return ResponseEntity.ok(productService.getProduct(productId));
    }
    @GetMapping()
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
}
