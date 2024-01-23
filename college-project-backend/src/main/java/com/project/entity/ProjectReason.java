package com.project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ProjectReason {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="prid")
    private int prid;
    private String prname;

}
