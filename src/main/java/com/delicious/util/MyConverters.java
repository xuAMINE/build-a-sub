package com.delicious.util;

import com.delicious.dto.ChipDTO;
import com.delicious.dto.DrinkDTO;
import com.delicious.dto.OrderDTO;
import com.delicious.dto.SandwichDTO;
import com.delicious.model.Chip;
import com.delicious.model.Drink;
import com.delicious.model.Order;
import com.delicious.model.Sandwich;

public class MyConverters {
    // example factory method
    public static EntityConverter<Order, OrderDTO> orderConverter() {
        return new EntityConverter<>(
                order -> OrderDTO.builder()
                        .id(order.getId())
                        .orderTime(order.getOrderTime())
                        .sandwiches(order.getSandwiches().stream()
                                .map(s -> SandwichDTO.builder()
                                        .id(s.getId())
                                        .name(s.getName())
                                        .basePrice(s.getBasePrice())
                                        .toppings(s.getToppings())
                                        .price(s.calculatePrice())
                                        .build())
                                .toList())
                        .drinks(order.getDrinks().stream()
                                .map(d -> DrinkDTO.builder()
                                        .id(d.getId())
                                        .name(d.getName())
                                        .price(d.getPrice())
                                        .build())
                                .toList())
                        .chips(order.getChips().stream()
                                .map(c -> ChipDTO.builder()
                                        .id(c.getId())
                                        .name(c.getName())
                                        .price(c.getPrice())
                                        .build())
                                .toList())
                        .total(order.getTotal())
                        .build(),

                dto -> Order.builder()
                        .orderTime(dto.getOrderTime())
                        .sandwiches(dto.getSandwiches().stream()
                                .map(sdto -> Sandwich.builder()
                                        .name(sdto.getName())
                                        .basePrice(sdto.getBasePrice())
                                        .toppings(sdto.getToppings())
                                        .build())
                                .toList())
                        .drinks(dto.getDrinks().stream()
                                .map(ddto -> Drink.builder()
                                        .name(ddto.getName())
                                        .price(ddto.getPrice())
                                        .build())
                                .toList())
                        .chips(dto.getChips().stream()
                                .map(cdto -> Chip.builder()
                                        .name(cdto.getName())
                                        .price(cdto.getPrice())
                                        .build())
                                .toList())
                        .build()
        );
    }
}
