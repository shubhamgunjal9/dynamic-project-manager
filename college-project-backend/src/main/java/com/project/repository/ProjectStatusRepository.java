package com.project.repository;

import com.project.entity.ProjectCatogory;
import com.project.entity.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface ProjectStatusRepository extends JpaRepository<ProjectStatus, Serializable> {

	
    @Modifying
    @Query(value = "UPDATE project_status SET psname = :status WHERE psid = :id", nativeQuery = true)
    void changeStatus(@Param("status") String status, @Param("id") int id);
    
    
    @Query(value = "select count(*) from project_status", nativeQuery = true)
    int ProjectCount();

    int countByPsnameEquals(String psname);
    
    
    
    @Query(value = "SELECT total FROM project_status", nativeQuery = true)
    List<Integer> getTotalData();

    @Query(value = "SELECT closed FROM project_status", nativeQuery = true)
    List<Integer> getClosedData();
    
    
    
    

}

