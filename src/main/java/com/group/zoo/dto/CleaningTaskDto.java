package com.group.zoo.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CleaningTaskDto {
    private Long id;
    private LocalDateTime plannedAt;
    private LocalDateTime doneAt;
    private String status;
    private Long enclosureId;
    private Long userId;
}
