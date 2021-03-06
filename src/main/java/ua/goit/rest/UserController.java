package ua.goit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.goit.dto.UserDto;
import ua.goit.services.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserDto get(@PathVariable Long id) {
        return userService.get(id);
    }

    @PostMapping
    public void create(@Valid @RequestBody UserDto userDto) {
        userService.create(userDto);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id,
                       @RequestBody UserDto userDto,
                       Model model) {
        userService.update(id, userDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/validation")
    public void validate(@RequestBody @Valid UserDto userDto) {

    }

}
