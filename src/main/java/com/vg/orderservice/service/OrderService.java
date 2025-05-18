package com.vg.orderservice.service;

import com.vg.orderservice.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.random.RandomGenerator;

@Service
@RequiredArgsConstructor
public class OrderService {
//    @Autowired
//    private OrderRepository orderRepository;

    public static Map<Integer, Order> inMemoryDB = new HashMap<>();

    public Order placeOrder(Order order) {
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PLACED");
//        return orderRepository.save(order);
        int id = RandomGenerator.getDefault().nextInt(0,100);
        order.setId((long) id);
        inMemoryDB.put(id, order);
        return order;
    }

    public List<Order> getOrdersByCustomerId(Long customerId) {
//        return orderRepository.findByCustomerId(customerId);
        return null;
    }

    public Order getOrderById(int orderId) {
//        return orderRepository.findById(Long.valueOf(orderId)).orElseThrow(() ->
//                new RuntimeException("Order not found")
        return inMemoryDB.get(orderId);
    }

    public Order updateOrderStatus(int orderId, String status) {
        Order order = getOrderById(orderId);
        order.setStatus(status);
//        return orderRepository.save(order);
        inMemoryDB.put(orderId, order);
        return order;
    }
}


