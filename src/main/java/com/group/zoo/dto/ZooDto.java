package com.group.zoo.dto;

import java.time.LocalTime;
import java.util.List;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class ZooDto {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    private String name;
    private String city;
    private String country;
    private LocalTime openFrom;
    private LocalTime openTo;
    private List<Long> enclosureIds;
    private List<Long> userIds;
}
