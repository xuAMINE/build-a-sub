package com.delicious.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PremiumTopping implements Topping {
    private String name;
    private double cost;
    private double premiumFee = 1.00;

    @Override
    public double getCost() {
        return cost + premiumFee;
    }
}
