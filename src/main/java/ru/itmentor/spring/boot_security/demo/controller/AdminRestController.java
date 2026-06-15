package ru.itmentor.spring.boot_security.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmentor.spring.boot_security.demo.dto.UserDto;
import ru.itmentor.spring.boot_security.demo.dto.UserRequest;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.serviсe.Serviсe;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Admin REST", description = "REST API для Admin панели")
@RestController
@RequestMapping("/api/admin")
public class AdminRestController {

    private final Serviсe userService;

    public AdminRestController(Serviсe userService) {
        this.userService = userService;
    }

    @Operation(summary = "Получить всех пользователей")
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers().stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Получить пользователя")
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable long id) {
        UserDto user = userService.getUserDto(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Создать нового пользователя")
    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@RequestBody UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(request));
    }

    @Operation(summary = "Обновить пользователя")
    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable long id, @RequestBody UserRequest request) {
        UserDto user = userService.updateUser(id, request);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Удалить пользователя по ID")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        User user = userService.getUser(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
