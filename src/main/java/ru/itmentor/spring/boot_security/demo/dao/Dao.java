package ru.itmentor.spring.boot_security.demo.dao;



import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;

public interface Dao {
    void saveUser(User user);

    void deleteUser(long id);

    void updateUser(User user);

    User getUser(long id);

    User findByUsername(String username);

    List<User> getAllUsers();
}
