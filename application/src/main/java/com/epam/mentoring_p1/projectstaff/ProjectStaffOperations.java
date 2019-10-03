package com.epam.mentoring_p1.projectstaff;

import com.epam.mentoring_p1.dtomodels.EmployeeDTO;
import com.epam.mentoring_p1.dtomodels.ProjectDTO;

import java.time.LocalDate;
import java.util.Set;

public interface ProjectStaffOperations {
    Set<EmployeeDTO> getDeveloperList(Long projectId);

    ProjectDTO addDeveloperToProject(Long projectId, Long employeeId, LocalDate endDate);

    ProjectDTO removeDeveloperFromProject(Long projectId, Long employeeId);
}
