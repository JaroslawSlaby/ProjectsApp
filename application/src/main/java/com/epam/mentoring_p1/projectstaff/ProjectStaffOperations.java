package com.epam.mentoring_p1.projectstaff;

import com.epam.mentoring_p1.models.Employee;

import java.time.LocalDate;
import java.util.Set;

public interface ProjectStaffOperations {
    Set<Employee> getDeveloperList(Long projectId);

    Boolean addDeveloperToProject(Long projectId, Long employeeId, LocalDate endDate);
}
