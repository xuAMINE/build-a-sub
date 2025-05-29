package com.delicious.controller;

import com.delicious.dto.ChipDTO;
import com.delicious.dto.DrinkDTO;
import com.delicious.dto.OrderDTO;
import com.delicious.dto.SandwichDTO;
import com.delicious.model.Chip;
import com.delicious.model.Drink;
import com.delicious.model.Order;
import com.delicious.model.Sandwich;
import com.delicious.service.OrderService;
import com.delicious.util.EntityConverter;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final EntityConverter<Order, OrderDTO> converter;

    public OrderController(OrderService orderService) {
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

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        // Initialize DTO with empty lists to avoid null pointers in the form preview
        OrderDTO dto = new OrderDTO();
        dto.setSandwiches(java.util.Collections.singletonList(new com.delicious.dto.SandwichDTO()));
        dto.setDrinks(new java.util.ArrayList<>());
        dto.setChips(new java.util.ArrayList<>());
        model.addAttribute("orderDto", dto);
        return "order/create";
    }

    @PostMapping
    public String submitOrder(@ModelAttribute("orderDto") @Valid OrderDTO dto,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            return "order/create";
        }
        // Ensure lists are not null to avoid NPE
        if (dto.getSandwiches() == null) dto.setSandwiches(new java.util.ArrayList<>());
        if (dto.getDrinks() == null) dto.setDrinks(new java.util.ArrayList<>());
        if (dto.getChips() == null) dto.setChips(new java.util.ArrayList<>());

        Order saved = orderService.placeOrder(converter.convertToEntity(dto));
        OrderDTO savedDto = converter.convertToDto(saved);
        model.addAttribute("order", savedDto);
        return "order/summary";
    }
}
