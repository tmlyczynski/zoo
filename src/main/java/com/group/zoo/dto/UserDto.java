package com.group.zoo.dto;

import java.util.List;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class UserDto {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    private String firstName;
    private String lastName;
    private String role;
    private String login;
    private Boolean active;
    private Long zooId;
    private List<Long> feedingIds;
    private List<Long> cleaningTaskIds;
}
