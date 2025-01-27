package com.masoud.orderservice.clients;

import com.masoud.orderservice.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "product-service",
        url = "${application.config.product-url}"
)
public interface ProductClient {
    @GetMapping("/{productId}")
    ProductResponse getProduct(@PathVariable Integer productId);

    @PostMapping("/{productId}")
    void purchaseProduct(@PathVariable Integer productId);


}
