package com.dao;

import com.model.entity.Role;

public interface RoleDao extends Dao<Role> {
    Role findByName(String name);
}
