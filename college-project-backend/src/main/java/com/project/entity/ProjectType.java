package com.project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ProjectType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ptid")
    private int ptid;
    private String ptname;
}
