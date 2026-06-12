package ru.itmentor.spring.boot_security.demo.dao;

import ru.itmentor.spring.boot_security.demo.model.Roles;

import java.util.List;

public interface RoleDao {
    void save(Roles role);

    Roles findByName(String name);

    List<Roles> findAll();
}
