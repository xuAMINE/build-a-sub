package com.delicious.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChipDTO {
    private Long id;
    private String name;
    private double price;
}