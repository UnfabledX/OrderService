package com.leka.orders.service;

import com.leka.orders.entity.OrderStatus;
import com.leka.orders.entity.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    OrderDto save(OrderDto orderDto);

    void deleteOrdersByUserIdAndStatus(Long userId, OrderStatus status);

    Page<OrderDto> getAllOrdersByUserId(Long userId, Pageable pageable);

    void deleteOrdersByOrderId(Long orderId);

    Page<OrderDto> getAllOrdersByOrderStatus(OrderStatus status, PageRequest pageable);

    Integer countOrdersByOrderStatus(OrderStatus status);

    Page<OrderDto> getAllOrders(PageRequest pageable);

    OrderDto getOrderById(Long orderId);

    OrderDto updateOrderByIdAndWithStatus(Long orderId, OrderStatus status);
}
