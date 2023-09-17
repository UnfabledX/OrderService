package com.leka.orders.service;

import com.leka.orders.entity.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    OrderDto save(OrderDto orderDto);

    void deleteOrdersByUserIdAndStatus(Long userId, String status);

    Page<OrderDto> getOrdersByUserId(Long userId, Pageable pageable);

    void deleteOrdersByOrderId(Long orderId);

    Page<OrderDto> getOrdersByOrderStatus(String status, PageRequest pageable);

    Integer countOrdersByOrderStatus(String status);

    Page<OrderDto> getAllOrders(PageRequest pageable);
}
