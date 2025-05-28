package com.delicious.service;

import com.delicious.model.Sandwich;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SandwichFactoryImpl implements SandwichFactory {
    @Override
    public Sandwich create(String name, double basePrice, List<String> toppings) {
        return Sandwich.builder()
                .name(name)
                .basePrice(basePrice)
                .toppings(toppings)
                .build();
    }
}
