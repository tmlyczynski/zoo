package com.group.zoo.dto;

import java.time.LocalDateTime;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class CleaningTaskDto {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    private LocalDateTime plannedAt;
    private LocalDateTime doneAt;
    private String status;
    private Long enclosureId;
    private Long userId;
}
