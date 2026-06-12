package ru.itmentor.spring.boot_security.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.serviсe.Serviсe;

@Tag(name = "Admin", description = "Админ панель и все пользователи")
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final Serviсe userService;

    public AdminController(Serviсe userService) {
        this.userService = userService;
    }

    @Operation(summary = "Админ панель ")
    @GetMapping
    public String adminPanel(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("newUser", new User());
        return "admin";
    }

    @Operation(summary = "Создать нового пользователя")
    @PostMapping("/save")
    public String saveUser(@ModelAttribute("newUser") User user, Model model) {
        userService.saveUser(user);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("newUser", new User());
        return "admin";
    }

    @Operation(summary = "Обновить пользователя")
    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user")User user, Model model) {
        userService.updateUser(user);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("newUser", new User());
        return "admin";
    }

    @Operation(summary = "Удалить пользователя")
    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") long id, Model model) {
        userService.deleteUser(id);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("newUser", new User());
        return "admin";
    }
}
