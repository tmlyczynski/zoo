package com.group.zoo.repository;

import com.group.zoo.domain.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface AnimalRepository extends JpaRepository<Animal, Long> {
     Page<Animal> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
