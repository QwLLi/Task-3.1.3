package ru.itmentor.spring.boot_security.demo.configs;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.itmentor.spring.boot_security.demo.dao.RoleDao;
import ru.itmentor.spring.boot_security.demo.model.Roles;

@Component
public class RoleText implements Converter<String, Roles> {

    private final RoleDao roleDao;

    public RoleText(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Roles convert(String roleName) {
        return roleDao.findByName(roleName);
    }
}