package com.leka.orders.service;

import com.leka.orders.NotFoundException;
import com.leka.orders.entity.Order;
import com.leka.orders.entity.OrderStatus;
import com.leka.orders.entity.dto.OrderDto;
import com.leka.orders.mapper.OrderMapper;
import com.leka.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderDto save(OrderDto orderDto) {
        Order order = orderMapper.toEntity(orderDto);
        order = orderRepository.save(order);
        return orderMapper.toDto(order);
    }

    @Override
    public void deleteOrdersByUserIdAndStatus(Long userId, String status) {
        OrderStatus orderStatus = getOrderStatus(status);
        orderRepository.deleteAllByUserIdAndOrderStatus(userId, orderStatus);
    }

    @Override
    public Page<OrderDto> getAllOrdersByUserId(Long userId, Pageable pageable) {
        return orderRepository.getOrdersByUserId(userId, pageable)
                .map(orderMapper::toDto);
    }

    @Override
    public void deleteOrdersByOrderId(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public Page<OrderDto> getAllOrdersByOrderStatus(String status, PageRequest pageable) {
        OrderStatus orderStatus = getOrderStatus(status);
        return orderRepository.getOrdersByOrderStatus(orderStatus, pageable)
                .map(orderMapper::toDto);
    }

    @Override
    public Integer countOrdersByOrderStatus(String status) {
        OrderStatus orderStatus = getOrderStatus(status);
        return orderRepository.countOrdersByOrderStatus(orderStatus);
    }

    @Override
    public Page<OrderDto> getAllOrders(PageRequest pageable) {
        return orderRepository.findAll(pageable)
                .map(orderMapper::toDto);
    }

    @Override
    public OrderDto getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::toDto)
                .orElseThrow(() -> new NotFoundException("Order is not found by id"));
    }

    @Override
    public OrderDto updateOrderByIdAndWithStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new NotFoundException("Order is not found by id"));
        OrderStatus orderStatus = getOrderStatus(status);
        order.setOrderStatus(orderStatus);
        order = orderRepository.save(order);
        return orderMapper.toDto(order);
    }

    private static OrderStatus getOrderStatus(String status) {
        return OrderStatus.valueOf(status.toUpperCase());
    }
}
