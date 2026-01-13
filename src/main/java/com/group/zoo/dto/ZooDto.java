package com.group.zoo.dto;

import java.time.LocalTime;
import java.util.List;

import lombok.Data;

@Data
public class ZooDto {
    private Long id;
    private String name;
    private String city;
    private String country;
    private LocalTime openFrom;
    private LocalTime openTo;
    private List<Long> enclosureIds;
    private List<Long> userIds;
}
