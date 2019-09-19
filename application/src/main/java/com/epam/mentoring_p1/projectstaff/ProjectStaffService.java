package com.epam.mentoring_p1.projectstaff;

import com.epam.mentoring_p1.models.Employee;
import com.epam.mentoring_p1.models.Project;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface ProjectStaffService {
    ResponseEntity<Set<Employee>> getProjectDevelopers(Long projectId);

    ResponseEntity<Project> addDeveloperToProject(Long projectId, Long employeeId, String endDate);

    ResponseEntity<Project> removeDeveloperFromProject(Long projectId, Long employeeId);
}
