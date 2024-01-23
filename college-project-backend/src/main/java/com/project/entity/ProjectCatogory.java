package com.project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ProjectCatogory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pcid")
    private int pcid;
    private String pcname;
   // private String ProjectName;
}
