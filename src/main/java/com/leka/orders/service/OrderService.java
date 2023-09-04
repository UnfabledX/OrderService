package com.leka.orders.service;

import com.leka.orders.entity.dto.OrderDto;

public interface OrderService {

    OrderDto save(OrderDto orderDto);

    void deleteOrdersByUserIdAndStatus(Long userId, String status);
}
