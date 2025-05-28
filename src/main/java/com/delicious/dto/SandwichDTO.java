package com.delicious.dto;

import java.util.List;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SandwichDTO {
    private Long id;
    private String name;
    private double basePrice;
    private List<String> toppings;
    private double price;
}