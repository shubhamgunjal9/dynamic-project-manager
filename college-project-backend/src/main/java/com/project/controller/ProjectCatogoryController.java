package com.project.controller;

import com.project.dto.ProjectDTO;
import com.project.entity.ProjectCatogory;
import com.project.entity.ProjectDept;
import com.project.entity.ProjectDivision;
import com.project.entity.ProjectInfo;
import com.project.entity.ProjectLocation;
import com.project.entity.ProjectPriority;
import com.project.entity.ProjectReason;
import com.project.entity.ProjectStatus;
import com.project.entity.ProjectType;
import com.project.service.ProjectCatagoryService;
import com.project.service.ProjectDeptService;
import com.project.service.ProjectDivisionService;
import com.project.service.ProjectInfoService;
import com.project.service.ProjectLocationService;
import com.project.service.ProjectPriorityService;
import com.project.service.ProjectReasonService;
import com.project.service.ProjectStatusService;
import com.project.service.ProjectTypeService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/projectcatogory")
public class ProjectCatogoryController {

	@Autowired
	private ProjectCatagoryService Cservice;

	@Autowired
	private ProjectInfoService pservice;

	@Autowired
	private ProjectReasonService reasonService; // Corrected autowiring

	@Autowired
	private ProjectTypeService typeService;

	@Autowired
	private ProjectDivisionService divisionService;

	@Autowired
	private ProjectPriorityService priorityService;

	@Autowired
	private ProjectDeptService deptService;

	@Autowired
	private ProjectLocationService locationService;

	@Autowired
	private ProjectStatusService statusService;

	@PostMapping("/save")
	public ProjectInfo getProjectCatogory(@RequestBody ProjectDTO data) {

		ProjectInfo pinfo = new ProjectInfo();
		// pinfo.setProid(data.getProjectId());
		pinfo.setProname(data.getProjectName());
		pinfo.setProstartdate(data.getProjectStartDate());
		pinfo.setProenddate(data.getProjectEndDate());

		ProjectReason pres = new ProjectReason();
		// pres.setPrid(data.getReasonId());
		pres.setPrname(data.getReasonName());
		pinfo.setProjectReason(pres);
		reasonService.saveProjectReason(pres);

		ProjectType ptype = new ProjectType();
		// ptype.setPtid(data.getTypeId());
		ptype.setPtname(data.getTypeName());
		pinfo.setProjectType(ptype);
		typeService.saveProjectType(ptype);

		ProjectDivision pdev = new ProjectDivision();
		// pdev.setPdivid(data.getDivisionId());
		pdev.setPdivname(data.getDivisionName());
		pinfo.setProjectDivision(pdev);
		divisionService.saveProjectDivision(pdev);

		ProjectCatogory pc = new ProjectCatogory();
		// pc.setPcid(data.getCategoryId());
		pc.setPcname(data.getCategoryName());
		pinfo.setProjectCategory(pc);
		Cservice.saveProjectCategory(pc);

		ProjectPriority prio = new ProjectPriority();
		// prio.setPpid(data.getPriorityId());
		prio.setPpname(data.getPriorityName());
		pinfo.setProjectPriority(prio);
		priorityService.saveProjectPriority(prio);

		ProjectDept pd = new ProjectDept();
		// pd.setPdid(data.getDeptId());
		pd.setPdname(data.getDeptName());
		pinfo.setProjectDept(pd);
		deptService.saveProjectDept(pd);

		ProjectLocation ploc = new ProjectLocation();
		// ploc.setPlid(data.getLocationId());
		ploc.setPlname(data.getLocationName());
		System.out.println(data.getLocationName());
		pinfo.setProjectLocation(ploc);

		ProjectStatus pstatus = new ProjectStatus();
		// pstatus.setPsid(data.getStatusId());
		pstatus.setPsname(data.getStatusName());
		pinfo.setProjectStatus(pstatus);
		locationService.saveProjectLocation(ploc);
		statusService.saveProjectStatus(pstatus);

		return pservice.saveProjectInfo(pinfo);
		// return new ResponseEntity<>(service.getProjectCatagory(), HttpStatus.OK);
	}

	@GetMapping("/getAllinfo")
	public List<ProjectInfo> getAllProjectInfos() {
		// Your implementation to get all project information
		return pservice.getAllProjectInfos();
	}

	@PostMapping("/changeStatus")
	@Transactional
	public List<ProjectInfo> change_status(@RequestBody Map<String, String> data) {

		String status = data.get("psname");
		int projectId = Integer.parseInt(data.get("psid"));
		System.out.println(projectId);
		System.out.println(status);
		statusService.changeStatus(status, projectId);

		return pservice.getAllProjectInfos();
	}

	@GetMapping("/projectcount")
	public Map<String, String> getCount() {

		Map<String, String> countMap = new HashMap<String, String>();

		int totalProjects = statusService.getProjectCountByPsid();
		int totalRunningProject = statusService.getProjectCountByPsname("Running");
		int totalClosedProject = statusService.getProjectCountByPsname("Closed");
		int totalCancelledProject = statusService.getProjectCountByPsname("Cancelled");
		int totalRegisteredProject = statusService.getProjectCountByPsname("Registerd");

		countMap.put("TotalProjects", String.valueOf(totalProjects));
		countMap.put("TotalRunningProject", String.valueOf(totalRunningProject));
		countMap.put("TotalClosedProject", String.valueOf(totalClosedProject));
		countMap.put("TotalCancelledProject", String.valueOf(totalCancelledProject));
		countMap.put("TotalRegisteredProject", String.valueOf(totalRegisteredProject));

		return countMap;
	}

	@GetMapping("/summary")
	public ResponseEntity<List<Object>> getDepartmentProjectSummary() {
		

		return new ResponseEntity<>(pservice.getDepartmentProjectSummary(),HttpStatus.OK);
	}

//	@PostMapping("/sort")
//	public ResponseEntity<List<ProjectInfo>> sortProjects(@RequestBody Map<String, String> data) {
//	    List<ProjectInfo> sortedProjects = pservice.getProjectInfos(); // Corrected method name
//
//	    return ResponseEntity.ok(sortedProjects);
//	}

	@GetMapping("/sort")
	public ResponseEntity<List<ProjectInfo>> sortProjects(@RequestParam String sortBy) {
	    List<ProjectInfo> sortedProjects;

	    switch (sortBy) {
	        case "category":
	            sortedProjects = pservice.getAllProjectInfosSortedByCategory();
	            break;

	        case "priority":
	            sortedProjects = pservice.getAllProjectInfosSortedByPriority();
	            break;

	        case "reason":
	            sortedProjects = pservice.getAllProjectInfosSortedByReason();
	            break;

	        case "division":
	            sortedProjects = pservice.getAllProjectInfosSortedByDivision();
	            break;

	        case "location":
	            sortedProjects = pservice.getAllProjectInfosSortedByLocation();
	            break;

	        case "department":
	            sortedProjects = pservice.getAllProjectInfosSortedByDepartment();
	            break;

	        default:
	            sortedProjects = pservice.getAllProjectInfos();
	            break;
	    }

	    return ResponseEntity.ok(sortedProjects);
	}

}
