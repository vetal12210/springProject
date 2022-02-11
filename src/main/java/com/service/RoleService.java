package com.service;

import com.model.entity.Role;
import com.dao.RoleDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@Transactional
public class RoleService {
    private final RoleDao roleDao;

    public RoleService(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public Role findById(Long id) {
        return roleDao.findById(id);
    }

    public Role findByName(String name) {
        return roleDao.findByName(name);
    }

    public Boolean existsByName(String name) {
        if (roleDao.findByName(name) == null) {
            log.info("existsByName: {} {}", name, false);
            return false;
        } else {
            log.info("existsByName: {} {}", name, true);
            return true;
        }
    }
}
