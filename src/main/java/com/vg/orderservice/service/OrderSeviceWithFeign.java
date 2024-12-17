package com.vg.orderservice.service;

import com.vg.orderservice.controller.CustomerClient;
import com.vg.orderservice.dao.OrderRepository;
import com.vg.orderservice.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderSeviceWithFeign {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerClient customerClient;

    public Order placeOrder(Order order) {
        // Validate customer before placing order
        customerClient.getCustomerDetails(order.getCustomerId());

        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PLACED");
        return orderRepository.save(order);
    }

    public List<Order> getOrdersByCustomerId(String customerId) {
        return orderRepository.findByCustomerId(customerId);
    }
}
