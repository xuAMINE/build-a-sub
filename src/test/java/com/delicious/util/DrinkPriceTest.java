package com.delicious.util;

import static org.junit.jupiter.api.Assertions.*;

import com.delicious.model.Drink;
import com.delicious.model.enums.DrinkSize;
import org.junit.jupiter.api.Test;

class DrinkPriceTest {

    @Test
    void getPrice_returnsEnumValue() {
        Drink small = Drink.builder().size(DrinkSize.SMALL).build();
        Drink large = Drink.builder().size(DrinkSize.LARGE).build();

        assertEquals(2.00, small.getPrice(), 0.001);
        assertEquals(3.00, large.getPrice(), 0.001);
    }
}