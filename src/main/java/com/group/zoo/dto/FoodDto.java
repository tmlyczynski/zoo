package com.group.zoo.dto;

import java.util.List;

import lombok.Data;

@Data
public class FoodDto {
    private Long id;
    private String name;
    private String dietType;
    private double caloriesPerServing;
    private List<Long> feedingIds;
}
