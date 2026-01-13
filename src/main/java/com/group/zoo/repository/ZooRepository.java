package com.group.zoo.repository;

import com.group.zoo.domain.entity.Zoo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZooRepository extends JpaRepository<Zoo, Long> {
}
