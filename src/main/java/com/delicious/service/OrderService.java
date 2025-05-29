package com.delicious.service;

import com.delicious.model.Order;
import com.delicious.model.Sandwich;

import java.util.List;

public interface OrderService {
    Order placeOrder(Order order);
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    Order update(Long id, Order order);
    void delete(Long id);
}