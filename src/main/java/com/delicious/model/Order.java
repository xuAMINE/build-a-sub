package com.delicious.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderTime;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Sandwich> sandwiches;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Drink> drinks;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Chip> chips;

    public double getTotal() {
        double sandwichTotal = sandwiches.stream()
                .mapToDouble(Sandwich::calculatePrice)
                .sum();
        double drinkTotal = drinks.stream()
                .mapToDouble(Drink::getPrice)
                .sum();
        double chipTotal = chips.stream()
                .mapToDouble(Chip::getPrice)
                .sum();
        return sandwichTotal + drinkTotal + chipTotal;
    }
}
