package com.group.zoo.dto;

import java.util.List;

import lombok.Data;

@Data
public class EnclosureDto {
    private Long id;
    private String name;
    private String type;
    private Long zooId;
    private List<Long> animalIds;
    private List<Long> cleaningTaskIds;
}
