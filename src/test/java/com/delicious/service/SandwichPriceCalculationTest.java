package com.delicious.service;

import static org.junit.jupiter.api.Assertions.*;

import com.delicious.model.Sandwich;
import com.delicious.model.enums.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class SandwichPriceCalculationTest {

    @Test
    void calculatePrice_correctlyAppliesRules() {
        Sandwich s = Sandwich.builder()
                .size(SandwichSize.EIGHT_INCH)
                .meats(Arrays.asList(MeatType.HAM, MeatType.ROAST_BEEF))
                .cheeses(Arrays.asList(CheeseType.AMERICAN, CheeseType.SWISS))
                .extraMeat(true)
                .extraCheese(true)
                .regularToppings(List.of(RegularToppingType.LETTUCE))
                .sauces(List.of(SauceType.MAYO))
                .sides(List.of(SideType.AU_JUS))
                .build();

        // EIGHT_INCH: base=7.0, toppingPrice=2.0, extraMeat=1.0, extraCheese=0.6
        double expected = 6.0
                + 2 * 2.0  // meats
                + 2 * 2.0  // cheeses
                + 1.0      // extra meat
                + 0.6;     // extra cheese
        assertEquals(expected, s.calculatePrice(), 0.001);
    }
}