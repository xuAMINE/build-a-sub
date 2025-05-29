package com.delicious.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

import com.delicious.model.enums.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Sandwich {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private BreadType breadType;

    @Enumerated(EnumType.STRING)
    private SandwichSize size;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<MeatType> meats;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<CheeseType> cheeses;

    private boolean extraMeat;
    private boolean extraCheese;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<RegularToppingType> regularToppings;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<SauceType> sauces;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<SideType> sides;

    public double calculatePrice() {
        double price = size.getBasePrice();
        // meat toppings cost
        price += meats.size() * size.getToppingPrice();
        // cheese toppings cost
        price += cheeses.size() * size.getCheesePrice();
        // extra meat
        if (extraMeat) price += size.getExtraMeatPrice();
        // extra cheese smaller increment = 30/60/90 cents
        double extraCheesePrice = switch(size) {
            case FOUR_INCH -> 0.30;
            case EIGHT_INCH -> 0.60;
            case TWELVE_INCH -> 0.90;
        };
        if (extraCheese) price += extraCheesePrice;
        // regular toppings, sauces, sides: free
        return price;
    }
}
