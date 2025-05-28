package com.delicious.service;

import com.delicious.model.Order;

public interface PriceCalculator {
    double calculateTotal(Order order);
}
