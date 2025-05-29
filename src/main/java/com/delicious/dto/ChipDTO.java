package com.delicious.dto;

import lombok.*;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ChipDTO {
    private Long id;
    private String type;
    private double price; // always 1.50
}