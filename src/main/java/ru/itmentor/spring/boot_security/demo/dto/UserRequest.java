package ru.itmentor.spring.boot_security.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserRequest {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private int year;
    private Set<String> roles;
}
