package com.store.demo.api.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.store.demo.api.security.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
