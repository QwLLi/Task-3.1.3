package ru.itmentor.spring.boot_security.demo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itmentor.spring.boot_security.demo.model.Roles;

import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Roles role) {
        entityManager.persist(role);
    }

    @Override
    @Transactional(readOnly = true)
    public Roles findByName(String name) {
        return entityManager.createQuery("from Roles where name = :name", Roles.class)
                .setParameter("name", name)
                .getResultList()
                .stream().findFirst().orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Roles> findAll() {
        return entityManager.createQuery("from Roles", Roles.class).getResultList();
    }
}
