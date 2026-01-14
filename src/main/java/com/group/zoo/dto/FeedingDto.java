package com.group.zoo.dto;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class FeedingDto {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    private String feedingTime;
    private Double amount;
    private String notes;
    private Long animalId;
    private Long userId;
    private Long foodId;
}
