package com.delicious.service;

import com.delicious.model.Sandwich;
import java.util.List;

public interface SandwichFactory {
    Sandwich create(String name, double basePrice, List<String> toppings);
}