package com.project.service;

import com.project.entity.ProjectStatus;
import com.project.repository.ProjectStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectStatusService {

    private final ProjectStatusRepository projectStatusRepository;

    @Autowired
    public ProjectStatusService(ProjectStatusRepository projectStatusRepository) {
        this.projectStatusRepository = projectStatusRepository;
    }

    public List<ProjectStatus> getAllProjectStatuses() {
        return projectStatusRepository.findAll();
    }

    public Optional<ProjectStatus> getProjectStatusById(int psid) {
        return projectStatusRepository.findById(psid);
    }

    public ProjectStatus saveProjectStatus(ProjectStatus projectStatus) {
        return projectStatusRepository.save(projectStatus);
    }

    public void deleteProjectStatus(int psid) {
        projectStatusRepository.deleteById(psid);
    }
    
    public void changeStatus(String status,int id) {
         projectStatusRepository.changeStatus(status, id);
    }
    
    public int getProjectCountByPsid() {
        return projectStatusRepository.ProjectCount();
    }

    public int getProjectCountByPsname(String psname) {
        return projectStatusRepository.countByPsnameEquals(psname);
    }
    
}
