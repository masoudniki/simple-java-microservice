package com.masoud.orderservice.controller;

import com.masoud.orderservice.request.CreateOrderRequest;
import com.masoud.orderservice.response.OrderResponse;
import com.masoud.orderservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    ResponseEntity<List<OrderResponse>> getOrders() {
        return ResponseEntity.ok(orderService.getOrders());
    }

    @GetMapping("{orderId}")
    ResponseEntity<OrderResponse> getOrder(@PathVariable Integer orderId) {
        return ResponseEntity.ok(orderService.getOrder(orderId));
    }

    @DeleteMapping("{orderId}")
    ResponseEntity<Void> deleteOrder(@PathVariable Integer orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    ResponseEntity<?> createOrder(@RequestBody @Valid CreateOrderRequest request){
        orderService.createOrder(request);
        return ResponseEntity.ok().build();
    }


}
