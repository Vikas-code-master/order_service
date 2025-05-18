package com.vg.orderservice.service;

import com.vg.orderservice.controller.CustomerClient;
import com.vg.orderservice.controller.ProductClient;
import com.vg.orderservice.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.random.RandomGenerator;

@Service
@RequiredArgsConstructor
public class OrderSeviceWithFeign {

//    @Autowired
//    private OrderRepository orderRepository;
    @Autowired
    private CustomerClient customerClient;
    @Autowired
    private ProductClient productClient;

    public Order placeOrder(Order order) {
        // Validate customer before placing order
        customerClient.getCustomerDetails(order.getCustomerId());
        int id = RandomGenerator.getDefault().nextInt();
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PLACED");
//        return orderRepository.save(order);
        OrderService.inMemoryDB.put(id, order);
        return order;
    }

    public List<Order> getOrdersByCustomerId(Long customerId) {
//        return orderRepository.findByCustomerId(customerId);
       return null;
    }
    public Object getProductWithOrderDetails(Integer orderId, Integer productId) {
//        Object orderDetails = orderRepository.findById(Long.valueOf(orderId))
//                .orElseThrow(() -> new RuntimeException("Product not found"));
        Object orderDetails = OrderService.inMemoryDB.get(orderId);
        Object product = productClient.getOrderDetails(productId);
        return new Object() {
            public Object productDetails = product;
            public Object order = orderDetails;
        };
    }
}
