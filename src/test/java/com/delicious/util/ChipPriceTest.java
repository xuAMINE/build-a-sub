package com.delicious.util;

import static org.junit.jupiter.api.Assertions.*;

import com.delicious.model.Chip;
import org.junit.jupiter.api.Test;

class ChipPriceTest {

    @Test
    void getPrice_always150() {
        Chip chip = Chip.builder().type("Salted").build();
        assertEquals(1.50, chip.getPrice(), 0.001);
    }
}