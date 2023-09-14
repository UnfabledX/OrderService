package com.leka.orders.controller;

import com.leka.orders.entity.dto.OrderDto;
import com.leka.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto save(@RequestBody OrderDto orderDto) {
        return orderService.save(orderDto);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOrdersByUserIdAndStatus(@RequestParam("id") Long userId,
                                              @RequestParam("status") String status) {
        orderService.deleteOrdersByUserIdAndStatus(userId, status);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOrdersByOrderId(@PathVariable("id") Long orderId) {
        orderService.deleteOrdersByOrderId(orderId);
    }

    @GetMapping("/{userId}")
    public Page<OrderDto> getOrdersByUserId(@PathVariable("userId") Long userId,
                                            @RequestParam(name = "page", defaultValue = "1") Integer pageNo,
                                            @RequestParam(name = "size", defaultValue = "10") Integer pageSize,
                                            @RequestParam(name = "sort", defaultValue = "createdAt") String sortField,
                                            @RequestParam(name = "dir", defaultValue = "asc") String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        PageRequest pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        return orderService.getOrdersByUserId(userId, pageable);
    }
}
