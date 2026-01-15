package com.group.zoo.repository;

import com.group.zoo.domain.entity.Feeding;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface FeedingRepository extends JpaRepository<Feeding, Long> {
	Page<Feeding> findAllByDeletedFalse(Pageable pageable);
	Optional<Feeding> findByIdAndDeletedFalse(Long id);
}
