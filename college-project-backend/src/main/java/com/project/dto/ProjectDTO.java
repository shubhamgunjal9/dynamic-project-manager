package com.project.dto;



import java.util.Date;

import lombok.Data;

@Data
public class ProjectDTO {
    private int categoryId;
    private String categoryName;

    private int deptId;
    private String deptName;

    private int divisionId;
    private String divisionName;

    private int projectId;
    private String projectName;
    private Date projectStartDate;
    private Date projectEndDate;

    private int locationId;
    private String locationName;

    private int priorityId;
    private String priorityName;

    private int reasonId;
    private String reasonName;

    private int statusId;
    private String statusName;

    private int typeId;
    private String typeName;

    // Constructors, getters, setters...

    public ProjectDTO(
            int categoryId, String categoryName,
            int deptId, String deptName,
            int divisionId, String divisionName,
            int projectId, String projectName, Date projectStartDate, Date projectEndDate,
            int locationId, String locationName,
            int priorityId, String priorityName,
            int reasonId, String reasonName,
            int statusId, String statusName,
            int typeId, String typeName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.deptId = deptId;
        this.deptName = deptName;
        this.divisionId = divisionId;
        this.divisionName = divisionName;
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectStartDate = projectStartDate;
        this.projectEndDate = projectEndDate;
        this.locationId = locationId;
        this.locationName = locationName;
        this.priorityId = priorityId;
        this.priorityName = priorityName;
        this.reasonId = reasonId;
        this.reasonName = reasonName;
        this.statusId = statusId;
        this.statusName = statusName;
        this.typeId = typeId;
        this.typeName = typeName;
    }

    // Add other constructors, getters, setters as needed
}
