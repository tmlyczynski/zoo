package com.group.zoo.dto;

import lombok.Data;

@Data
public class FeedingDto {
    private Long id;
    private String feedingTime;
    private Double amount;
    private String notes;
    private Long animalId;
    private Long userId;
    private Long foodId;
}
