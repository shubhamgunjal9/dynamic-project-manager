package com.project.service;

import com.project.entity.ProjectCatogory;
import com.project.repository.ProjectCatogoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectCatagoryService {
    @Autowired
    private ProjectCatogoryRepository repository;

    public List<ProjectCatogory> getProjectCatagory() {
        List<ProjectCatogory> list = repository.findAll();
        return list;
    }
    
    public void saveProjectCategory(ProjectCatogory obj){
    	repository.save(obj);
    }
}
