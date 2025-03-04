package com.masoud.orderservice.clients;

import com.masoud.orderservice.request.PurchaseProductRequest;
import com.masoud.orderservice.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "product-service",
        url = "${application.config.product-url}"
)
public interface ProductClient {
    @GetMapping("/{productId}")
    ProductResponse getProduct(@PathVariable Integer productId);

    @PostMapping(value = "/purchase",consumes = MediaType.APPLICATION_JSON_VALUE)
    void purchaseProduct(@RequestBody PurchaseProductRequest request);


}
