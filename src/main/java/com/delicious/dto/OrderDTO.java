package com.delicious.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
    private Long id;
    private LocalDateTime orderTime;
    private List<SandwichDTO> sandwiches;
    private List<DrinkDTO> drinks;
    private List<ChipDTO> chips;
    private double total;
}