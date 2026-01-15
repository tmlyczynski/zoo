package com.group.zoo.repository;

import com.group.zoo.domain.entity.Food;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {
    Page<Food> findByNameContainingIgnoreCaseAndDeletedFalse(String name, Pageable pageable);
    Page<Food> findAllByDeletedFalse(Pageable pageable);
    Optional<Food> findByIdAndDeletedFalse(Long id);
}
}
