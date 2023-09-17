package com.leka.orders.repository;

import com.leka.orders.entity.Order;
import com.leka.orders.entity.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    void deleteAllByUserIdAndOrderStatus(Long userId, OrderStatus orderStatus);

    Page<Order> getOrdersByUserId(Long userId, Pageable pageable);

    Page<Order> getOrdersByOrderStatus(OrderStatus status, Pageable pageable);

    Integer countOrdersByOrderStatus(OrderStatus status);
}
