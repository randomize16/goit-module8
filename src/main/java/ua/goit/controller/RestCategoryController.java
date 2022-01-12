package ua.goit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.goit.dto.CategoryDto;
import ua.goit.services.CategoryService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/categories")
public class RestCategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping
    public List<CategoryDto> getAll() {
        return service.getAllDto();
    }

    @PostMapping
    public void create(@Valid @RequestBody CategoryDto dto) {
        service.create(dto);
    }
}
