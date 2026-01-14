package com.group.zoo.dto;

import java.util.List;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class FoodDto {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    private String name;
    private String dietType;
    private double caloriesPerServing;
    private List<Long> feedingIds;
}
