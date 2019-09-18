package com.epam.mentoring_p1.projectstaff;

import com.epam.mentoring_p1.models.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Set;

@RestController
public class ProjectStaffController {

    private final ProjectStaffService projectStaffService;

    public ProjectStaffController(ProjectStaffService projectStaffService) {
        this.projectStaffService = projectStaffService;
    }

    @GetMapping("/projectStaff/{projectId}")
    public ResponseEntity<Set<Employee>> getProjectDevelopers(@PathVariable(value = "projectId") Long projectId) {
        return projectStaffService.getProjectDevelopers(projectId);
    }

    @PostMapping("/projectStaff/{projectId}/addDev/{employeeId}/date/{endDate}")
    public ResponseEntity<Boolean> addDeveloperToProject
            (@PathVariable(value = "projectId") Long projectId,
             @PathVariable(value = "employeeId") Long employeeId,
             @PathVariable(value = "endDate") String endDate) {
        return projectStaffService.addDeveloperToProject(projectId, employeeId, endDate);
    }

    @DeleteMapping("/projectStaff/{projectId}/removeDev/{employeeId}")
    public ResponseEntity<Employee> removeDeveloperFromProject
            (@PathVariable(value = "projectId") Long projectId,
             @PathVariable(value = "employeeId") Long employeeId) {
        return projectStaffService.removeDeveloperFromProject(projectId, employeeId);
    }
}
