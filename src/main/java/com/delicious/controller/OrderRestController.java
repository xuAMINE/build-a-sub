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
    private final EntityConverter<Order, OrderDTO> converter;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
        this.converter = new EntityConverter<>(
                // Entity → DTO
                order -> OrderDTO.builder()
                        .id(order.getId())
                        .customerName(order.getCustomerName())
                        .orderTime(order.getOrderTime())
                        .sandwiches(order.getSandwiches().stream()
                                .map(s -> SandwichDTO.builder()
                                        .id(s.getId())
                                        .breadType(s.getBreadType())
                                        .size(s.getSize())
                                        .meats(s.getMeats())
                                        .cheeses(s.getCheeses())
                                        .extraMeat(s.isExtraMeat())
                                        .extraCheese(s.isExtraCheese())
                                        .regularToppings(s.getRegularToppings())
                                        .sauces(s.getSauces())
                                        .sides(s.getSides())
                                        .price(s.calculatePrice())
                                        .build())
                                .collect(Collectors.toList()))
                        .drinks(order.getDrinks().stream()
                                .map(d -> DrinkDTO.builder()
                                        .id(d.getId())
                                        .size(d.getSize())
                                        .price(d.getPrice())
                                        .build())
                                .collect(Collectors.toList()))
                        .chips(order.getChips().stream()
                                .map(c -> ChipDTO.builder()
                                        .id(c.getId())
                                        .type(c.getType())
                                        .price(c.getPrice())
                                        .build())
                                .collect(Collectors.toList()))
                        .total(order.getTotal())
                        .build(),
                // DTO → Entity
                dto -> Order.builder()
                        .customerName(dto.getCustomerName())
                        .sandwiches(dto.getSandwiches().stream()
                                .map(sdto -> Sandwich.builder()
                                        .breadType(sdto.getBreadType())
                                        .size(sdto.getSize())
                                        .meats(sdto.getMeats())
                                        .cheeses(sdto.getCheeses())
                                        .extraMeat(sdto.isExtraMeat())
                                        .extraCheese(sdto.isExtraCheese())
                                        .regularToppings(sdto.getRegularToppings())
                                        .sauces(sdto.getSauces())
                                        .sides(sdto.getSides())
                                        .build())
                                .collect(Collectors.toList()))
                        .drinks(dto.getDrinks().stream()
                                .map(ddto -> Drink.builder()
                                        .size(ddto.getSize())
                                        .build())
                                .collect(Collectors.toList()))
                        .chips(dto.getChips().stream()
                                .map(cdto -> Chip.builder()
                                        .type(cdto.getType())
                                        .build())
                                .collect(Collectors.toList()))
                        .build()
        );
    }

    @PostMapping
    public OrderDTO create(@RequestBody @Valid OrderDTO dto) {
        var saved = orderService.placeOrder(converter.convertToEntity(dto));
        return converter.convertToDto(saved);
    }

    @GetMapping("/{id}")
    public OrderDTO get(@PathVariable Long id) {
        return converter.convertToDto(orderService.getOrderById(id));
    }

    @GetMapping
    public List<OrderDTO> list() {
        return orderService.getAllOrders()
                .stream()
                .map(converter::convertToDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public OrderDTO update(@PathVariable Long id, @RequestBody @Valid OrderDTO dto) {
        var updated = orderService.update(id, converter.convertToEntity(dto));
        return converter.convertToDto(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderService.delete(id);
    }
}
