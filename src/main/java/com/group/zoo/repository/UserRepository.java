package com.group.zoo.repository;

import com.group.zoo.domain.entity.User;
import com.group.zoo.domain.enums.UserRole;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findByUserRole(UserRole userRole, Pageable pageable);
}
