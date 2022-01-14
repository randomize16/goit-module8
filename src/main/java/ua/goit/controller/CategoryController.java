package ua.goit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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

    @PostMapping("/{id}/upload")
    public String uploadImg(@RequestParam MultipartFile file,
                            RedirectAttributes attributes,
                            @PathVariable Long id) {
        service.uploadImage(id, file);
        attributes.addFlashAttribute("message",
                "File was uploaded. " + file.getName());
        return "redirect:/categories/" + id;
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String name,
                         @RequestParam String description,
                         @RequestParam(required = false) String parent) {
        return "redirect:/categories";
    }

    @GetMapping(value = "/{id}/image", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getImage(@PathVariable Long id) {
        return service.getImage(id);
    }

}
