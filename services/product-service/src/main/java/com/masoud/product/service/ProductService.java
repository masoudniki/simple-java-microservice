package com.masoud.product.service;

import ch.qos.logback.core.util.StringUtil;
import com.masoud.product.exception.exceptions.CategoryNotFoundException;
import com.masoud.product.exception.exceptions.ProductAvailabilityException;
import com.masoud.product.exception.exceptions.ProductNotFoundException;
import com.masoud.product.mapper.ProductMapper;
import com.masoud.product.mapper.ReservedProductMapper;
import com.masoud.product.model.Category;
import com.masoud.product.model.Product;
import com.masoud.product.model.ReservedProduct;
import com.masoud.product.repository.CategoryRepository;
import com.masoud.product.repository.ProductRepository;
import com.masoud.product.repository.ReservedProductRepository;
import com.masoud.product.request.ProductCreateRequest;
import com.masoud.product.request.ProductUpdateRequest;
import com.masoud.product.response.ProductResponse;
import com.masoud.product.response.ReservedProductResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ReservedProductRepository reservedProductRepository;
    private final ReservedProductMapper reservedProductMapper;
    private final CategoryRepository categoryRepository;

    public Integer createProduct(ProductCreateRequest request) {

        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(
                        () -> new CategoryNotFoundException("there is no category with id " + request.categoryId())
                );



        var product = Product.builder()
                .name(request.name())
                .description(request.description())
                .availabilityQuantity(request.availableQuantity())
                .price(request.price())
                .category(category)
                        .build();

        return productRepository.save(product).getId();
    }
    public Product updateProduct(Integer productId,ProductUpdateRequest request){
        var product = productRepository.findById(productId).orElseThrow(()-> new ProductNotFoundException("there is not any product with given information."));

        mergeProduct(request,product);

        return productRepository.save(product);
    }

    public ProductResponse getProduct(Integer productId) {
        return productRepository.findById(productId)
                .map(productMapper::toProductResponse)
                .orElseThrow(() -> new ProductNotFoundException("There is not any product with the given information."));
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

    @Transactional
    public ReservedProductResponse purchaseProduct(Integer productId, String customerId) {
        // find the produce
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("There is not any product with the given information."));
        // check product availability
        if(product.getAvailabilityQuantity() < 0){
            throw new ProductAvailabilityException("There is not any available products at this moment.");
        }
        // mark product as reserved for a user

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime reservedUntil = now.plusMinutes(15);

        ReservedProduct reservedProduct = ReservedProduct.builder()
                .product(product)
                .customerId(customerId)
                .createdAt(now)
                .reservedUntil(reservedUntil)
                .build();

        product.setAvailabilityQuantity(product.getAvailabilityQuantity()-1);

        productRepository.save(product);
        reservedProductRepository.save(reservedProduct);

        // return reserved time
        return reservedProductMapper.toReservedProductResponse(reservedProduct);
    }
}
