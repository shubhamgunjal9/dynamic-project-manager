package com.project.service;

import com.project.entity.ProjectDept;
import com.project.repository.ProjectDeptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectDeptService {

    private final ProjectDeptRepository projectDeptRepository;

    @Autowired
    public ProjectDeptService(ProjectDeptRepository projectDeptRepository) {
        this.projectDeptRepository = projectDeptRepository;
    }

    public List<ProjectDept> getAllProjectDepts() {
        return projectDeptRepository.findAll();
    }

    public Optional<ProjectDept> getProjectDeptById(int pdid) {
        return projectDeptRepository.findById(pdid);
    }

    public ProjectDept saveProjectDept(ProjectDept projectDept) {
        return projectDeptRepository.save(projectDept);
    }

    public void deleteProjectDept(int pdid) {
        projectDeptRepository.deleteById(pdid);
    }
}

