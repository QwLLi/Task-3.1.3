package ru.itmentor.spring.boot_security.demo.serviсe;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmentor.spring.boot_security.demo.dao.Dao;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;


@Service
public class ServiceImpl implements Serviсe {

    private final Dao dao;
    private final PasswordEncoder passwordEncoder;

    public ServiceImpl(Dao dao, PasswordEncoder passwordEncoder) {
        this.dao = dao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.saveUser(user);
    }

    @Override
    @Transactional
    public void saveUser(String login, String password, String firstName, String lastName, int year) {
        User user = new User(login, passwordEncoder.encode(password), firstName, lastName, year);
        dao.saveUser(user);
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        dao.deleteUser(id);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        dao.updateUser(user);
    }

    @Override
    public User getUser(long id) {
        return dao.getUser(id);
    }

    @Override
    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }
}

