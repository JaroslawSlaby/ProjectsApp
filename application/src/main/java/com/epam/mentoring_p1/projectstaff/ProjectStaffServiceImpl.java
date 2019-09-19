package com.epam.mentoring_p1.projectstaff;

import com.epam.mentoring_p1.models.Employee;
import com.epam.mentoring_p1.models.Project;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Service
public class ProjectStaffServiceImpl implements ProjectStaffService {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
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
    public ResponseEntity<Project> addDeveloperToProject(Long projectId, Long employeeId, String endDate) {
        Project project = projectStaffOperations.addDeveloperToProject(projectId, employeeId, LocalDate.parse(endDate, formatter));
        return createResponse(project);
    }

    @Override
    public ResponseEntity<Project> removeDeveloperFromProject(Long projectId, Long employeeId) {
        Project project = projectStaffOperations.removeDeveloperFromProject(projectId, employeeId);
        return createResponse(project);
    }

    private ResponseEntity<Project> createResponse(Project project) {
        return new ResponseEntity<>(project, project != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
