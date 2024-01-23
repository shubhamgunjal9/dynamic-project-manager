package com.project.repository;

import com.project.entity.ProjectCatogory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface ProjectCatogoryRepository extends JpaRepository<ProjectCatogory, Serializable> {
}
