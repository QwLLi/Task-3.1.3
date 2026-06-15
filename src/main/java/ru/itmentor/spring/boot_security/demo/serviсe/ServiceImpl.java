package ru.itmentor.spring.boot_security.demo.serviсe;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmentor.spring.boot_security.demo.dao.Dao;
import ru.itmentor.spring.boot_security.demo.dao.RoleDao;
import ru.itmentor.spring.boot_security.demo.dto.UserDto;
import ru.itmentor.spring.boot_security.demo.dto.UserRequest;
import ru.itmentor.spring.boot_security.demo.model.Roles;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class ServiceImpl implements Serviсe {

    private final Dao dao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;

    public ServiceImpl(Dao dao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.dao = dao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserDto createUser(UserRequest request) {
        User user = new User(
                request.getUsername(),
                request.getPassword(),
                request.getFirstName(),
                request.getLastName(),
                request.getYear()
        );
        if (request.getRoles() != null) {
            Set<Roles> roles = request.getRoles().stream()
                    .map(name -> roleDao.findByName(name))
                    .collect(Collectors.toSet());
            user.setRoles(roles);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.saveUser(user);
        return new UserDto(user);
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
    @Transactional
    public UserDto updateUser(long id, UserRequest request) {
        User existing = dao.getUser(id);
        if (existing == null) {
            return null;
        }
        User updateUser = new User(
                existing.getUsername(),
                existing.getPassword(),
                request.getFirstName(),
                request.getLastName(),
                request.getYear()
        );
        updateUser.setId(id);
        if (request.getRoles() != null) {
            Set<Roles> roles = request.getRoles().stream()
                    .map(Roles::new)
                    .collect(Collectors.toSet());
            updateUser.setRoles(roles);
        }
        dao.updateUser(updateUser);
        User updated = dao.getUser(id);
        return new UserDto(updated);
    }

    @Override
    public User getUser(long id) {
        return dao.getUser(id);
    }

    @Override
    public UserDto getUserDto(long id) {
        User user = dao.getUser(id);
        if (user == null) {
            return null;
        }
        return new UserDto(user);
    }

    @Override
    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }
}

