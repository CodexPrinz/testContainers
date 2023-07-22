package com.projects.testContainers.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;


import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class UserDto implements Serializable {
    @Id
    @JsonIgnore
    private Integer id;
    private String name;
    private String email;
    private Timestamp created_at;
}
