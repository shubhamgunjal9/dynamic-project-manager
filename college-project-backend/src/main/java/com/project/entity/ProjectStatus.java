package com.project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ProjectStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "psid")
    private int psid;
    private String psname;
}
