package com.group.zoo.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class AnimalDto {
    private Long id;
    private String name;
    private String species;
    private LocalDate dateOfBirth;
    private String type;
    private String diet;
    private Long enclosureId;
    private Long healthCardId;
    private List<Long> feedingIds;
}
