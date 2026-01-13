package com.group.zoo.dto;

import java.util.List;
import lombok.Data;

@Data
public class UserDto {
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
