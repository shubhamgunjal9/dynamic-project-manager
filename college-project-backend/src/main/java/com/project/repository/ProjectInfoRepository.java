package com.project.repository;

import com.project.entity.ProjectCatogory;
import com.project.entity.ProjectInfo;

import jakarta.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

public interface ProjectInfoRepository extends JpaRepository<ProjectInfo, Serializable> {
    List<ProjectInfo> findByPronameContainingIgnoreCase(String projectName);

    List<ProjectInfo> findByProjectTypePtid(int ptid);

    List<ProjectInfo> findByProjectReasonPrid(int prid);
    
    
    
    @Query("SELECT pd.pdname, " +
    	       "       SUM(CASE WHEN ps.psname = 'Closed' THEN 1 ELSE 0 END) as closedProjects, " +
    	       "       COUNT(*) as totalProjects " +
    	       "FROM ProjectInfo pi " +
    	       "JOIN pi.projectDept pd " +
    	       "JOIN pi.projectStatus ps " +
    	       "GROUP BY pd.pdname")
    	List<Object> getDepartmentProjectSummary();
    
    
    @Query("FROM ProjectInfo pi ORDER BY CASE pi.projectPriority.ppname " +
            "WHEN 'high' THEN 1 " +
            "WHEN 'medium' THEN 2 " +
            "WHEN 'low' THEN 3 " +
            "ELSE 4 END")
    List<ProjectInfo> getAllProjectInfosSortedByPriority();

    
    @Query("FROM ProjectInfo pi ORDER BY CASE pi.projectCategory.pcname " +
            "WHEN 'Quality A' THEN 1 " +
            "WHEN 'Quality B' THEN 2 " +
            "WHEN 'Quality C' THEN 3 " +
            "WHEN 'Quality D' THEN 4 " +
            "ELSE 5 END")
    List<ProjectInfo> getProjectInfoListByCategoryOrder();

    @Query("FROM ProjectInfo pi ORDER BY pi.projectReason.prname ASC")
    List<ProjectInfo> getProjectInfoListByReasonOrder();
    
    @Query("FROM ProjectInfo pi ORDER BY pi.projectDivision.pdivname ASC")
    List<ProjectInfo> getProjectInfoListByDivisionOrder();

    @Query("FROM ProjectInfo pi ORDER BY pi.projectLocation.plname ASC")
    List<ProjectInfo> getProjectInfoListByLocationOrder();

    @Query("FROM ProjectInfo pi ORDER BY pi.projectDept.pdname ASC")
    List<ProjectInfo> getProjectInfoListByDepartmentOrder();



    
}