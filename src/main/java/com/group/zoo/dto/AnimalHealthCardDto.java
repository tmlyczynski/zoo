package com.group.zoo.dto;


import java.time.LocalDate;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class AnimalHealthCardDto {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    private LocalDate lastCheckupDate;
    private String veterinarianName;
    private Long animalId;
}
