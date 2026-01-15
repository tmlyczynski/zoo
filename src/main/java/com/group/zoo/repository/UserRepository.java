package com.group.zoo.repository;

import com.group.zoo.domain.entity.User;
import com.group.zoo.domain.enums.UserRole;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findByUserRoleAndDeletedFalse(UserRole userRole, Pageable pageable);
    Page<User> findAllByDeletedFalse(Pageable pageable);
    Optional<User> findByIdAndDeletedFalse(Long id);
}
}
