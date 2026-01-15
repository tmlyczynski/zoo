package com.group.zoo.repository;

import com.group.zoo.domain.entity.Enclosure;
import com.group.zoo.domain.enums.EnclosureType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnclosureRepository extends JpaRepository<Enclosure, Long> {
    Page<Enclosure> findByType(EnclosureType type, Pageable pageable);
}
