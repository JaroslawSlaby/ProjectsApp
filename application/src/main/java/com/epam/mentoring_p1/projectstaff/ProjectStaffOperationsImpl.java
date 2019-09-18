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
        Optional<Project> project = projectRepository.findById(projectId);
        return project.orElseThrow(getProjectNotFoundException(projectId))
                .getDevelopers()
                .stream()
                .map(Contract::getEmployee)
                .collect(Collectors.toSet());

    }

    @Override
    public Boolean addDeveloperToProject(Long projectId, Long employeeId, LocalDate endDate) {
        Optional<Project> projectFromDB = projectRepository.findById(projectId);
        Project project = projectFromDB.orElseThrow(getProjectNotFoundException(projectId));

        Optional<Employee> employeeFromDB = employeeRepository.findById(employeeId);
        Employee employee = employeeFromDB.orElseThrow(getEmployeeNotFoundException(projectId));

        boolean isAdded = project.addDeveloper(employee, endDate);
        projectRepository.save(project);
        return isAdded;
    }

    private Supplier<EmployeeNotFoundException> getEmployeeNotFoundException(Long employeeId) {
        return () -> new EmployeeNotFoundException("Employee not found: "+ employeeId);
    }

    private Supplier<ProjectNotFoundException> getProjectNotFoundException(Long projectId) {
        return () -> new ProjectNotFoundException("Project not found: " + projectId);
    }
}
