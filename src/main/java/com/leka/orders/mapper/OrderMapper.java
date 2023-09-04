package com.leka.orders.mapper;

import com.leka.orders.entity.Order;
import com.leka.orders.entity.dto.OrderDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toEntity(OrderDto dto);

    OrderDto toDto(Order order);
}
