package com.epam.mentoring_p1.projectstaff;

import com.epam.mentoring_p1.models.Employee;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Set;

public interface ProjectStaffService {
    ResponseEntity<Set<Employee>> getProjectDevelopers(Long projectId);

    ResponseEntity<Boolean> addDeveloperToProject(Long projectId, Long employeeId, String endDate);

    ResponseEntity<Employee> removeDeveloperFromProject(Long projectId, Long employeeId);
}
