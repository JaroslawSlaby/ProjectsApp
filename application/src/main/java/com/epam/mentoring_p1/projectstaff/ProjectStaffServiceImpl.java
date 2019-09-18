package com.epam.mentoring_p1.projectstaff;

import com.epam.mentoring_p1.models.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Service
public class ProjectStaffServiceImpl implements ProjectStaffService {

    private final ProjectStaffOperations projectStaffOperations;

    public ProjectStaffServiceImpl(ProjectStaffOperations projectStaffOperations) {
        this.projectStaffOperations = projectStaffOperations;
    }

    @Override
    public ResponseEntity<Set<Employee>> getProjectDevelopers(Long projectId) {
        Set<Employee> developers = projectStaffOperations.getDeveloperList(projectId);
        return new ResponseEntity<>(developers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> addDeveloperToProject(Long projectId, Long employeeId, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
        Boolean isAdded = projectStaffOperations.addDeveloperToProject(projectId, employeeId, LocalDate.parse(endDate, formatter));
        return null;
    }

    @Override
    public ResponseEntity<Employee> removeDeveloperFromProject(Long projectId, Long employeeId) {
        return null;
    }
}
