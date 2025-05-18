package com.vg.orderservice.controller;

import com.vg.orderservice.model.Order;
import com.vg.orderservice.service.OrderService;
import com.vg.orderservice.service.OrderSeviceWithFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderSeviceWithFeign orderSeviceWithFeign;

    @PostMapping("/create")
    public Order placeOrder(@RequestBody Order order) {
        return orderService.placeOrder(order);
    }

    @GetMapping("/customer/{customerId}")
    public List<Order> getOrdersByCustomer(@PathVariable Long customerId) {
        return orderService.getOrdersByCustomerId(customerId);
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable int id) {
        return orderService.getOrderById(id);
    }

    @PatchMapping("/{id}/status")
    public Order updateOrderStatus(@PathVariable int id, @RequestParam String status) {
        return orderService.updateOrderStatus(id, status);
    }
    @GetMapping("/{id}/product/{productId}")
    public Object getProductWithOrderDetails(@PathVariable int id, @PathVariable int productId) {
        return orderSeviceWithFeign.getProductWithOrderDetails(id, productId);
    }
}

