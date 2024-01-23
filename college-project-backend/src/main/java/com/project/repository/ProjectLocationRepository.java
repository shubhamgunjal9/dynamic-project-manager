package com.project.repository;

import com.project.entity.ProjectCatogory;
import com.project.entity.ProjectLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface ProjectLocationRepository extends JpaRepository<ProjectLocation, Serializable> {
}
