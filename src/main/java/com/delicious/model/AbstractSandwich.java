package com.delicious.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class AbstractSandwich {
    private Long id;
    private String name;
    private double basePrice;

    // template method
    public double calculatePrice() {
        return basePrice + additionalCost();
    }

    protected abstract double additionalCost();
}
