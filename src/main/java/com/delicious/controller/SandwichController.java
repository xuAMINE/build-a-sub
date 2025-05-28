package com.delicious.controller;

import com.delicious.model.Sandwich;
import com.delicious.service.SandwichFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sandwiches")
public class SandwichController {
    private final SandwichFactory sandwichFactory;

    public SandwichController(SandwichFactory sandwichFactory) {
        this.sandwichFactory = sandwichFactory;
    }

    @GetMapping
    public String listSandwiches(Model model) {
        List<Sandwich> sandwiches = List.of(
                sandwichFactory.create("Turkey Club", 5.99, List.of("Lettuce", "Tomato")),
                sandwichFactory.create("Veggie Delight", 4.99, List.of("Cucumber", "Avocado"))
        );
        model.addAttribute("sandwiches", sandwiches);
        return "sandwich/list";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("sandwich", new Sandwich());
        return "sandwich/form";
    }

    @PostMapping
    public String addSandwich(@ModelAttribute Sandwich sandwich) {
        return "redirect:/sandwiches";
    }
}