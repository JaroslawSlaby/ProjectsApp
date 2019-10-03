package com.epam.mentoring_p1.projectstaff;

import com.epam.mentoring_p1.dtomodels.EmployeeDTO;
import com.epam.mentoring_p1.dtomodels.ProjectDTO;
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
    public ResponseEntity<Set<EmployeeDTO>> getProjectDevelopers(Long projectId) {
        Set<EmployeeDTO> developers = projectStaffOperations.getDeveloperList(projectId);
        return new ResponseEntity<>(developers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProjectDTO> addDeveloperToProject(Long projectId, Long employeeId, String endDate) {
        ProjectDTO project = projectStaffOperations.addDeveloperToProject(projectId, employeeId, LocalDate.parse(endDate, formatter));
        return createResponse(project);
    }

    @Override
    public ResponseEntity<ProjectDTO> removeDeveloperFromProject(Long projectId, Long employeeId) {
        ProjectDTO project = projectStaffOperations.removeDeveloperFromProject(projectId, employeeId);
        return createResponse(project);
    }

    private ResponseEntity<ProjectDTO> createResponse(ProjectDTO project) {
        return new ResponseEntity<>(project, project != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
