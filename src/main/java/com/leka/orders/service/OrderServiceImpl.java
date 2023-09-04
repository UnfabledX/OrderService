package com.leka.orders.service;

import com.leka.orders.entity.Order;
import com.leka.orders.entity.OrderStatus;
import com.leka.orders.entity.dto.OrderDto;
import com.leka.orders.mapper.OrderMapper;
import com.leka.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
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
        OrderStatus orderStatus = OrderStatus.valueOf(status.toUpperCase());
        orderRepository.deleteAllByUserIdAndOrderStatus(userId, orderStatus);
    }
}
