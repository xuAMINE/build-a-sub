package com.delicious.service;

import java.time.LocalDateTime;
import java.util.List;

import com.delicious.model.Sandwich;
import org.springframework.stereotype.Service;
import com.delicious.model.Order;
import com.delicious.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order placeOrder(Order order) {
        order.setOrderTime(LocalDateTime.now());
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public Order update(Long id, Order order) {
        return orderRepository.findById(id)
                .map(existing -> {
                    existing.setCustomerName(order.getCustomerName());
                    existing.setSandwiches(order.getSandwiches());
                    existing.setDrinks(order.getDrinks());
                    existing.setChips(order.getChips());
                    return orderRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}