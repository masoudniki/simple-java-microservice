package com.masoud.product.service;

import ch.qos.logback.core.util.StringUtil;
import com.masoud.product.exception.exceptions.ProductNotFoundException;
import com.masoud.product.mapper.ProductMapper;
import com.masoud.product.model.Category;
import com.masoud.product.model.Product;
import com.masoud.product.repository.ProductRepository;
import com.masoud.product.request.ProductCreateRequest;
import com.masoud.product.request.ProductUpdateRequest;
import com.masoud.product.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    public Integer createProduct(ProductCreateRequest request) {
        var product = Product.builder()
                .name(request.name())
                .description(request.description())
                .availabilityQuantity(request.availableQuantity())
                .price(request.price())
                        .build();

        return productRepository.save(product).getId();
    }
    public Product updateProduct(Integer productId,ProductUpdateRequest request){
        var product = productRepository.findById(productId).orElseThrow(()-> new ProductNotFoundException("there is not any product with given information."));

        mergeProduct(request,product);

        return productRepository.save(product);
    }

    public Product getProduct(Integer productId){
        return productRepository.findById(productId).orElseThrow(()-> new ProductNotFoundException("there is not any product with given information."));
    }
    public List<ProductResponse> getAllProducts(){
        return productRepository.findAll().stream().map(productMapper::toProductResponse).toList();
    }


    private void mergeProduct(ProductUpdateRequest request, Product product){
        if(StringUtil.notNullNorEmpty(request.name())){
            product.setName(request.name());
        }
        if(StringUtil.notNullNorEmpty(request.description())){
            product.setDescription(request.description());
        }
        if (request.price() != null){
            product.setPrice(request.price());
        }
        if(request.availabilityQuantity() > 0) {
            product.setAvailabilityQuantity(request.availabilityQuantity());
        }
        if(request.categoryId() != null){
            Category category = product.getCategory();
            product.setCategory(category);
        }
    }


    public void deleteProduct(Integer productId) {
        productRepository.deleteById(productId);
    }
}
