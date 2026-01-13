package com.group.zoo.repository;

import com.group.zoo.domain.entity.CleaningTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CleaningTaskRepository extends JpaRepository<CleaningTask, Long> {
}
