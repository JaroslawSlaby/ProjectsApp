package com.epam.mentoring_p1.projectstaff;

import com.epam.mentoring_p1.models.Employee;
import com.epam.mentoring_p1.models.Project;

import java.time.LocalDate;
import java.util.Set;

public interface ProjectStaffOperations {
    Set<Employee> getDeveloperList(Long projectId);

    Project addDeveloperToProject(Long projectId, Long employeeId, LocalDate endDate);

    Project removeDeveloperFromProject(Long projectId, Long employeeId);
}
