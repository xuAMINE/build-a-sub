package com.delicious.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sandwich extends AbstractSandwich {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double basePrice;

    @ElementCollection
    private List<String> toppings;

    @Override
    protected double additionalCost() {
        // example: $0.5 per topping
        return toppings.stream()
                .mapToDouble(t -> 0.5)
                .sum();
    }
}
