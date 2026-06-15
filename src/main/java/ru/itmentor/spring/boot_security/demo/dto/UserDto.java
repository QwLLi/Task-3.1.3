package ru.itmentor.spring.boot_security.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private int year;
    private Set<String> roles;

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.year = user.getYear();
        this.roles = user.getRoles().stream()
                .map(r -> r.getName())
                .collect(Collectors.toSet());
    }
}
