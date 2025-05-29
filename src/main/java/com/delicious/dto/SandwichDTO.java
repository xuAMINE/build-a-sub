package com.delicious.dto;

import java.util.List;
import lombok.*;

import com.delicious.model.enums.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class SandwichDTO {
    private Long id;
    private BreadType breadType;
    private SandwichSize size;
    private List<MeatType> meats;
    private List<CheeseType> cheeses;
    private boolean extraMeat;
    private boolean extraCheese;
    private List<RegularToppingType> regularToppings;
    private List<SauceType> sauces;
    private List<SideType> sides;
    private double price;  // computed server-side
}