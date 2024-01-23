package com.project.repository;

import com.project.entity.ProjectCatogory;
import com.project.entity.ProjectPriority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface ProjectPriorityReposiory extends JpaRepository<ProjectPriority, Serializable> {
}
