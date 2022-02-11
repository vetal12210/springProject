package com.service;

import com.model.entity.User;
import com.dao.UserDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j2
@Transactional
public class UserService {
    private final UserDao userDao;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserDao userDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void create(User user) {
        String encode = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        log.info("create: {}", user.getLogin());
        userDao.create(user);
    }

    public void update(User user) {
        log.info("update: {}", user.getLogin());
        userDao.update(user);
    }

    public void remove(User user) {
        log.info("remove: {}", user.getLogin());
        userDao.remove(user);
    }

    public User findById(Long userId) {
        log.info("findById: {}", userId);
        return userDao.findById(userId);
    }

    public User findByLogin(String login) {
        log.info("findByLogin: {}", login);
        return userDao.findByLogin(login);
    }

    public Boolean existsByLogin(String login) {
        if (userDao.findByLogin(login) == null) {
            log.info("existsByLogin: {} {}", login, false);
            return false;
        } else {
            log.info("existsByLogin: {} {}", login, true);
            return true;
        }
    }

    public User findByEmail(String email) {
        log.info("findByEmail: {}", email);
        return userDao.findByEmail(email);
    }

    public Boolean existsByEmail(String email) {
        if (userDao.findByEmail(email) == null) {
            log.info("existsByEmail: {} {}", email, false);
            return false;
        } else {
            log.info("existsByEmail: {} {}", email, true);
            return true;
        }
    }

    public List<User> findAll() {
        List<User> all = userDao.findAll();
        log.info("Found {} users: {}", all.size(), all);
        return all;
    }
}
