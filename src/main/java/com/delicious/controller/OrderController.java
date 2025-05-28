package com.delicious.controller;

import com.delicious.model.Order;
import com.delicious.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String listOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "order/list";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("order", new Order());
        return "order/form";
    }

    @PostMapping
    public String placeOrder(@ModelAttribute Order order) {
        orderService.placeOrder(order);
        return "redirect:/orders";
    }
}