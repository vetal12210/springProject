package com.hibernate;

import com.model.entity.User;
import com.dao.UserDao;
import lombok.extern.log4j.Log4j2;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Log4j2
@Repository
public class HibernateUserDao implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(User entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(User entity) {
        entityManager.merge(entity);
    }

    @Override
    public void remove(User entity) {
        entityManager.remove(entity);
    }

    @Override
    public List<User> findAll() {
        Query<User> query = (Query<User>) entityManager.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }

    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findByLogin(String login) {
        Query<User> query = (Query<User>) entityManager.createQuery("SELECT u FROM User u WHERE u.login=:login", User.class);
        query.setParameter("login", login);
        return query.uniqueResult();
    }

    @Override
    public User findByEmail(String email) {
        Query<User> query = (Query<User>) entityManager.createQuery("SELECT u FROM User u WHERE u.email=:email", User.class);
        query.setParameter("email", email);
        return query.uniqueResult();
    }
}
