package com.delicious.model;

import com.delicious.model.enums.DrinkSize;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Drink {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DrinkSize size;

    public double getPrice() {
        return size.getPrice();
    }
}
