package com.masoud.orderservice.mappers;

import com.masoud.orderservice.model.Order;
import com.masoud.orderservice.response.OrderResponse;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
    public OrderResponse toOrderResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getCustomerId(),
                order.getCreatedAt()
        );
    }
}
