package com.projects.testContainers.db.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="created_at")
    private Timestamp created_at;
}
