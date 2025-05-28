package com.delicious.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DrinkDTO {
    private Long id;
    private String name;
    private double price;
}