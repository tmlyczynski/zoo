package com.group.zoo.repository;

import com.group.zoo.domain.entity.Food;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
    Page<Food> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
