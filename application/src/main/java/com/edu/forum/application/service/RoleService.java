package com.edu.forum.application.service;

import com.edu.forum.application.model.entity.Role;

public interface RoleService {
    Iterable<Role> findAll();

    void save(Role role);

    Role findByName(String name);
}

