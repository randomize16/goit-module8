package ua.goit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.goit.model.Category;
import ua.goit.services.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("categories", service.getAll());
        return "categories";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("categories", service.getAll());
        return "category";
    }

    @GetMapping("/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("category", service.get(id));
        model.addAttribute("categories", service.getAll());
        return "category";
    }

}
