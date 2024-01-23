package com.project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ProjectPriority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ppid")
    private int ppid;
    private String ppname;
}

