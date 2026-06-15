package ru.itmentor.spring.boot_security.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmentor.spring.boot_security.demo.dto.UserDto;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.serviсe.Serviсe;

@Tag(name = "User REST", description = "REST API для текущего пользователя")
@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private final Serviсe userService;

    public UserRestController(Serviсe userService) {
        this.userService = userService;
    }

    @Operation(summary = "Получить профиль текущего пользователя")
    @GetMapping("/profile")
    public ResponseEntity<UserDto> getProfile(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(new UserDto(user));
    }
}
