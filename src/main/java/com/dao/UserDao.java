package com.dao;

import com.model.entity.User;

public interface UserDao extends Dao<User> {
    User findByLogin(String login);

    User findByEmail(String email);
}
