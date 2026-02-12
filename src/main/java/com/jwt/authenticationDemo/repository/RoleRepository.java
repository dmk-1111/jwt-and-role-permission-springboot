package com.jwt.authenticationDemo.repository;

import com.jwt.authenticationDemo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    boolean existsByRole(String role);
}
