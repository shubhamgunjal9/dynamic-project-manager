package com.project.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class ProjectInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proid")
    private int proid;
    private String proname;
    private Date prostartdate;
    private Date proenddate;
    @ManyToOne
    @JoinColumn(name="prid")
    private ProjectReason projectReason;
    @ManyToOne
    @JoinColumn(name="ptid")
    private ProjectType projectType;
    @ManyToOne
    @JoinColumn(name="pdivid")
    private ProjectDivision projectDivision;
    @ManyToOne
    @JoinColumn(name="pcid")
    private ProjectCatogory projectCategory;
    @ManyToOne
    @JoinColumn(name="ppid")
    private ProjectPriority projectPriority;
    @ManyToOne
    @JoinColumn(name="pdid")
    private ProjectDept projectDept;
    @ManyToOne
    @JoinColumn(name="plid")
    private ProjectLocation projectLocation;
    @ManyToOne
    @JoinColumn(name="psid")
    private ProjectStatus projectStatus;
}




