package com.delicious.controller;

import com.delicious.dto.OrderDTO;
import com.delicious.model.Order;
import com.delicious.service.OrderService;
import com.delicious.util.EntityConverter;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.delicious.dto.*;
import com.delicious.model.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderRestController {

    private final OrderService orderService;
    private final EntityConverter<Order,OrderDTO> converter;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
        this.converter = new EntityConverter<>(
                // Entity → DTO
                order -> OrderDTO.builder()
                        .id(order.getId())
                        .customerName(order.getCustomerName())
//                        .orderNumber(order.getOrderNumber())
                        .orderTime(order.getOrderTime())
                        .sandwiches(order.getSandwiches().stream()
                                .map(s -> SandwichDTO.builder()
                                        .id(s.getId())
                                        .name(s.getName())
                                        .basePrice(s.getBasePrice())
                                        .toppings(s.getToppings())
                                        .price(s.calculatePrice())
                                        .build())
                                .collect(Collectors.toList()))
                        .drinks(order.getDrinks().stream()
                                .map(d -> DrinkDTO.builder()
                                        .id(d.getId())
                                        .name(d.getName())
                                        .price(d.getPrice())
                                        .build())
                                .collect(Collectors.toList()))
                        .chips(order.getChips().stream()
                                .map(c -> ChipDTO.builder()
                                        .id(c.getId())
                                        .name(c.getName())
                                        .price(c.getPrice())
                                        .build())
                                .collect(Collectors.toList()))
                        .total(order.getTotal())
                        .build(),
                // DTO → Entity
                dto -> Order.builder()
                        .customerName(dto.getCustomerName())
//                        .orderNumber(dto.getOrderNumber())
                        .orderTime(dto.getOrderTime())
                        .sandwiches(dto.getSandwiches().stream()
                                .map(sdto -> Sandwich.builder()
                                        .name(sdto.getName())
                                        .basePrice(sdto.getBasePrice())
                                        .toppings(sdto.getToppings())
                                        .build())
                                .collect(Collectors.toList()))
                        .drinks(dto.getDrinks().stream()
                                .map(ddto -> Drink.builder()
                                        .name(ddto.getName())
                                        .price(ddto.getPrice())
                                        .build())
                                .collect(Collectors.toList()))
                        .chips(dto.getChips().stream()
                                .map(cdto -> Chip.builder()
                                        .name(cdto.getName())
                                        .price(cdto.getPrice())
                                        .build())
                                .collect(Collectors.toList()))
                        .build()
        );
    }

    @GetMapping
    public List<OrderDTO> list() {
        return orderService.getAllOrders()
                .stream()
                .map(converter::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public OrderDTO get(@PathVariable Long id) {
        return converter.convertToDto(orderService.getOrderById(id));
    }

    @PostMapping
    public OrderDTO create(@RequestBody @Valid OrderDTO dto) {
        var saved = orderService.placeOrder(converter.convertToEntity(dto));
        return converter.convertToDto(saved);
    }
}
