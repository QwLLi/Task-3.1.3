package ru.itmentor.spring.boot_security.demo.serviсe;


import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;

public interface Serviсe {

    void saveUser(User user);


    void saveUser(String login, String password, String firstName, String lastName, int year);

    void deleteUser(long id);

    void updateUser(User user);

    User getUser(long id);

    List<User> getAllUsers();
}
