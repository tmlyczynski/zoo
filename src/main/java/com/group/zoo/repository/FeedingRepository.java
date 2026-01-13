package com.group.zoo.repository;

import com.group.zoo.domain.entity.Feeding;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedingRepository extends JpaRepository<Feeding, Long> {
}
