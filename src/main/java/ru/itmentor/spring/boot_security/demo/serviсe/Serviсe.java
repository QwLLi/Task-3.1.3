package ru.itmentor.spring.boot_security.demo.serviсe;


import ru.itmentor.spring.boot_security.demo.dto.UserDto;
import ru.itmentor.spring.boot_security.demo.dto.UserRequest;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;

public interface Serviсe {

    UserDto createUser(UserRequest request);

    void saveUser(User user);


    void saveUser(String login, String password, String firstName, String lastName, int year);

    void deleteUser(long id);

    void updateUser(User user);

    UserDto updateUser(long id, UserRequest request);

    User getUser(long id);

    UserDto getUserDto(long id);

    List<User> getAllUsers();
}
