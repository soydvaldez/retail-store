package com.store.demo.api.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.store.demo.api.security.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}