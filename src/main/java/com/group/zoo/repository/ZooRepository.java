package com.group.zoo.repository;

import com.group.zoo.domain.entity.Zoo;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface ZooRepository extends JpaRepository<Zoo, Long> {
	Page<Zoo> findAllByDeletedFalse(Pageable pageable);
	Optional<Zoo> findByIdAndDeletedFalse(Long id);
}
