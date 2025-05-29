package com.delicious.controller;

import com.delicious.model.Sandwich;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.delicious.dto.SandwichDTO;
import com.delicious.repository.SandwichRepository;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sandwiches")
public class SandwichRestController {

    private final SandwichRepository sandwichRepository;

    public SandwichRestController(SandwichRepository sandwichRepository) {
        this.sandwichRepository = sandwichRepository;
    }

    @GetMapping
    public List<SandwichDTO> list() {
        return sandwichRepository.findAll().stream()
                .map(s -> SandwichDTO.builder()
                        .id(s.getId())
                        .name(s.getName())
                        .basePrice(s.getBasePrice())
                        .toppings(s.getToppings())
                        .price(s.calculatePrice())
                        .build())
                .collect(Collectors.toList());
    }

    @PostMapping
    public SandwichDTO create(@RequestBody SandwichDTO dto) {
        Sandwich toSave = Sandwich.builder()
                .name(dto.getName())
                .basePrice(dto.getBasePrice())
                .toppings(dto.getToppings())
                .build();
        Sandwich saved = sandwichRepository.save(toSave);
        return SandwichDTO.builder()
                .id(saved.getId())
                .name(saved.getName())
                .basePrice(saved.getBasePrice())
                .toppings(saved.getToppings())
                .price(saved.calculatePrice())
                .build();
    }
}
