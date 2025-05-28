package com.delicious.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegularTopping implements Topping {
    private String name;
    private double cost;
}
