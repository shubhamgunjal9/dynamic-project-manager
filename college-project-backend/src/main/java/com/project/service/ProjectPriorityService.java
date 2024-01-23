package com.project.service;

import com.project.entity.ProjectPriority;
import com.project.repository.ProjectPriorityReposiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectPriorityService {

    private final ProjectPriorityReposiory projectPriorityRepository;

    @Autowired
    public ProjectPriorityService(ProjectPriorityReposiory projectPriorityRepository) {
        this.projectPriorityRepository = projectPriorityRepository;
    }

    public List<ProjectPriority> getAllProjectPriorities() {
        return projectPriorityRepository.findAll();
    }

    public Optional<ProjectPriority> getProjectPriorityById(int ppid) {
        return projectPriorityRepository.findById(ppid);
    }

    public ProjectPriority saveProjectPriority(ProjectPriority projectPriority) {
        return projectPriorityRepository.save(projectPriority);
    }

    public void deleteProjectPriority(int ppid) {
        projectPriorityRepository.deleteById(ppid);
    }
}
