package com.project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ProjectDivision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pdivid")
    private int pdivid;
    private String pdivname;

}
