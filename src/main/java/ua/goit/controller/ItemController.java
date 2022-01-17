package ua.goit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.goit.dto.ItemDto;
import ua.goit.model.Item;
import ua.goit.services.CategoryService;
import ua.goit.services.ItemService;

@Controller
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("items", itemService.getAll());
        return "items";
    }

    @GetMapping("/new")
    public String newItem(Model model) {
        model.addAttribute("categories", categoryService.getAll());
        return "item";
    }

    @PostMapping
    public String saveNew(@ModelAttribute ItemDto item) {
        itemService.save(item);
        return "redirect:/items";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable Long id, Model model) {
        model.addAttribute("item", itemService.get(id));
        return "item";
    }
}
