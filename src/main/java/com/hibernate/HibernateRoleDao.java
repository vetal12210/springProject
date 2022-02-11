package com.hibernate;

import com.model.entity.Role;
import com.dao.RoleDao;
import lombok.extern.log4j.Log4j2;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Log4j2
@Repository
public class HibernateRoleDao implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Role entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(Role entity) {
        entityManager.merge(entity);
    }

    @Override
    public void remove(Role entity) {
        entityManager.remove(entity);
    }

    @Override
    public List<Role> findAll() {
        Query<Role> query = (Query<Role>) entityManager.createQuery("SELECT r FROM Role r", Role.class);
        return query.getResultList();
    }

    @Override
    public Role findById(Long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public Role findByName(String name) {
        Query<Role> query = (Query<Role>) entityManager.createQuery("SELECT r FROM Role r WHERE r.name=:name", Role.class);
        query.setParameter("name", name);
        return query.uniqueResult();
    }
}

