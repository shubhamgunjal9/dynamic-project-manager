package com.project.service;

import com.project.entity.ProjectType;
import com.project.repository.ProjectTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectTypeService {

    private final ProjectTypeRepository projectTypeRepository;

    @Autowired
    public ProjectTypeService(ProjectTypeRepository projectTypeRepository) {
        this.projectTypeRepository = projectTypeRepository;
    }

    public List<ProjectType> getAllProjectTypes() {
        return projectTypeRepository.findAll();
    }

    public Optional<ProjectType> getProjectTypeById(int ptid) {
        return projectTypeRepository.findById(ptid);
    }

    public ProjectType saveProjectType(ProjectType projectType) {
        return projectTypeRepository.save(projectType);
    }

    public void deleteProjectType(int ptid) {
        projectTypeRepository.deleteById(ptid);
    }
}
