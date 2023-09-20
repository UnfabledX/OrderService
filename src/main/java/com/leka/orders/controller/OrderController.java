package com.leka.orders.controller;

import com.leka.orders.entity.dto.OrderDto;
import com.leka.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/users/{userId}")
    public Page<OrderDto> getAllOrdersByUserId(@PathVariable("userId") Long userId,
                                               @RequestParam(name = "page", defaultValue = "1") Integer pageNo,
                                               @RequestParam(name = "size", defaultValue = "10") Integer pageSize,
                                               @RequestParam(name = "sort", defaultValue = "createdAt") String sortField,
                                               @RequestParam(name = "dir", defaultValue = "asc") String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        PageRequest pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        return orderService.getAllOrdersByUserId(userId, pageable);
    }

    @GetMapping("/{status}")
    public Page<OrderDto> getAllOrdersByOrderStatus(@PathVariable("status") String status,
                                                    @RequestParam(name = "page", defaultValue = "1") Integer pageNo,
                                                    @RequestParam(name = "size", defaultValue = "10") Integer pageSize,
                                                    @RequestParam(name = "sort", defaultValue = "createdAt") String sortField,
                                                    @RequestParam(name = "dir", defaultValue = "asc") String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        PageRequest pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        return orderService.getAllOrdersByOrderStatus(status, pageable);
    }

    @GetMapping("/{status}/count")
    public Integer countOrdersByOrderStatus(@PathVariable("status") String status) {
        return orderService.countOrdersByOrderStatus(status);
    }

    @GetMapping("")
    public Page<OrderDto> getAllOrders(@RequestParam(name = "page", defaultValue = "1") Integer pageNo,
                                       @RequestParam(name = "size", defaultValue = "10") Integer pageSize,
                                       @RequestParam(name = "sort", defaultValue = "createdAt") String sortField,
                                       @RequestParam(name = "dir", defaultValue = "asc") String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        PageRequest pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        return orderService.getAllOrders(pageable);
    }

    @GetMapping("/{orderId}")
    public OrderDto getOrderById(@PathVariable("orderId") Long orderId) {
        return orderService.getOrderById(orderId);
    }

    @PutMapping("/update/{orderId}")
    public OrderDto updateOrderByIdAndWithStatus(@PathVariable("orderId") Long orderId,
                                               @RequestParam("status") String status){
        return orderService.updateOrderByIdAndWithStatus(orderId, status);
    }
}
