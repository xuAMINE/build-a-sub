package com.delicious.service;

import com.delicious.model.Order;
import org.springframework.stereotype.Component;

@Component
public class PriceCalculatorImpl implements PriceCalculator {
    @Override
    public double calculateTotal(Order order) {
        return order.getTotal();
    }
}