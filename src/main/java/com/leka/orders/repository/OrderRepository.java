package com.leka.orders.repository;

import com.leka.orders.entity.Order;
import com.leka.orders.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    void deleteAllByUserIdAndOrderStatus(Long userId, OrderStatus orderStatus);
}
