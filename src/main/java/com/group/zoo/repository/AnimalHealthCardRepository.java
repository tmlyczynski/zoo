package com.group.zoo.repository;

import com.group.zoo.domain.entity.AnimalHealthCard;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalHealthCardRepository extends JpaRepository<AnimalHealthCard, Long> {
    Page<AnimalHealthCard> findByVeterinarianNameContainingIgnoreCase(String veterinarianName, Pageable pageable);
}
