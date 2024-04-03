package com.edu.forum.application.repository;

import com.edu.forum.application.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
//    Role findByName(String name);

    Optional<Role> findByName(String name);
}
