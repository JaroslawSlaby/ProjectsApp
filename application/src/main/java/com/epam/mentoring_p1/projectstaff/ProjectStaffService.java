package com.epam.mentoring_p1.projectstaff;

import com.epam.mentoring_p1.dtomodels.EmployeeDTO;
import com.epam.mentoring_p1.dtomodels.ProjectDTO;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface ProjectStaffService {
    ResponseEntity<Set<EmployeeDTO>> getProjectDevelopers(Long projectId);

    ResponseEntity<ProjectDTO> addDeveloperToProject(Long projectId, Long employeeId, String endDate);

    ResponseEntity<ProjectDTO> removeDeveloperFromProject(Long projectId, Long employeeId);
}
