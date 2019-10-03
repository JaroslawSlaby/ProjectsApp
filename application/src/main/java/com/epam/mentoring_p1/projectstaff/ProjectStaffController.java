package com.epam.mentoring_p1.projectstaff;

import com.epam.mentoring_p1.dtomodels.EmployeeDTO;
import com.epam.mentoring_p1.dtomodels.ProjectDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class ProjectStaffController {

    private final ProjectStaffService projectStaffService;

    ProjectStaffController(ProjectStaffService projectStaffService) {
        this.projectStaffService = projectStaffService;
    }

    @GetMapping("/projectStaff/{projectId}")
    ResponseEntity<Set<EmployeeDTO>> getProjectDevelopers(@PathVariable(value = "projectId") Long projectId) {
        return projectStaffService.getProjectDevelopers(projectId);
    }

    @PostMapping("/projectStaff/{projectId}/addDev/{employeeId}/date/{endDate}")
    ResponseEntity<ProjectDTO> addDeveloperToProject
            (@PathVariable(value = "projectId") Long projectId,
             @PathVariable(value = "employeeId") Long employeeId,
             @PathVariable(value = "endDate") String endDate) {
        return projectStaffService.addDeveloperToProject(projectId, employeeId, endDate);
    }

    @DeleteMapping("/projectStaff/{projectId}/removeDev/{employeeId}")
    ResponseEntity<ProjectDTO> removeDeveloperFromProject
            (@PathVariable(value = "projectId") Long projectId,
             @PathVariable(value = "employeeId") Long employeeId) {
        return projectStaffService.removeDeveloperFromProject(projectId, employeeId);
    }
}
