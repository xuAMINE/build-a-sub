package com.delicious.model.enums;

import lombok.Getter;

@Getter
public enum SandwichSize {

    FOUR_INCH(5.50, 1.00, 0.75, 0.50),
    EIGHT_INCH(7.00, 2.00, 1.50,  1.00),
    TWELVE_INCH(8.50, 3.00, 2.25, 1.50);

    private final double basePrice;
    private final double toppingPrice;
    private final double cheesePrice;
    private final double extraMeatPrice;

    SandwichSize(double basePrice, double toppingPrice, double cheesePrice, double extraMeatPrice) {
        this.basePrice = basePrice;
        this.toppingPrice = toppingPrice;
        this.cheesePrice = cheesePrice;
        this.extraMeatPrice = extraMeatPrice;
    }
}
