package com.epam.mentoring_p1.projectstaff;

import com.epam.mentoring_p1.employees.EmployeeRepository;
import com.epam.mentoring_p1.exceptions.EmployeeNotFoundException;
import com.epam.mentoring_p1.exceptions.ProjectNotFoundException;
import com.epam.mentoring_p1.models.Contract;
import com.epam.mentoring_p1.models.Employee;
import com.epam.mentoring_p1.models.Project;
import com.epam.mentoring_p1.projects.ProjectRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class ProjectStaffOperationsImpl implements ProjectStaffOperations {

    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;

    public ProjectStaffOperationsImpl(ProjectRepository projectRepository, EmployeeRepository employeeRepository) {
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Set<Employee> getDeveloperList(Long projectId) {
        Optional<Project> project = getProjectFromDB(projectId);
        return project.orElseThrow(getProjectNotFoundException(projectId))
                .getDevelopers()
                .stream()
                .map(Contract::getEmployee)
                .collect(Collectors.toSet());
    }

    @Override
    public Project addDeveloperToProject(Long projectId, Long employeeId, LocalDate endDate) {
        Project project = getProject(projectId);
        Employee employee = getEmployee(employeeId);
        project.addDeveloper(employee, endDate);
        return projectRepository.save(project);
    }

    @Override
    public Project removeDeveloperFromProject(Long projectId, Long employeeId) {
        Project project = getProject(projectId);
        Employee employee = getEmployee(employeeId);
        project.removeDeveloper(employee);
        return projectRepository.save(project);
    }

    private Employee getEmployee(Long employeeId) {
        Optional<Employee> employeeFromDB = getEmployeeFromDB(employeeId);
        return employeeFromDB.orElseThrow(getEmployeeNotFoundException(employeeId));
    }

    private Project getProject(Long projectId) {
        Optional<Project> projectFromDB = getProjectFromDB(projectId);
        return projectFromDB.orElseThrow(getProjectNotFoundException(projectId));
    }

    private Optional<Project> getProjectFromDB(Long projectId) {
        return projectRepository.findById(projectId);
    }

    private Optional<Employee> getEmployeeFromDB(Long employeeId) {
        return employeeRepository.findById(employeeId);
    }

    private Supplier<EmployeeNotFoundException> getEmployeeNotFoundException(Long employeeId) {
        return () -> new EmployeeNotFoundException("Employee not found: "+ employeeId);
    }

    private Supplier<ProjectNotFoundException> getProjectNotFoundException(Long projectId) {
        return () -> new ProjectNotFoundException("Project not found: " + projectId);
    }
}
