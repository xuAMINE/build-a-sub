package com.delicious.dto;

import com.delicious.model.enums.DrinkSize;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class DrinkDTO {
    private Long id;
    private DrinkSize size;
    private double price; // computed
}