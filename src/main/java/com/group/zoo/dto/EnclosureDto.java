package com.group.zoo.dto;

import java.util.List;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class EnclosureDto {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    private String name;
    private String type;
    private Long zooId;
    private List<Long> animalIds;
    private List<Long> cleaningTaskIds;
}
