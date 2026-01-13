package com.group.zoo.dto;


import java.time.LocalDate;
import lombok.Data;

@Data
public class AnimalHealthCardDto {
    private Long id;
    private LocalDate lastCheckupDate;
    private String veterinarianName;
    private Long animalId;
}
