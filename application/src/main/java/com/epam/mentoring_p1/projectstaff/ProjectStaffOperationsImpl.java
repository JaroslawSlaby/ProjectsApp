package com.epam.mentoring_p1.projectstaff;

import com.epam.mentoring_p1.dtomodels.ContractDTO;
import com.epam.mentoring_p1.dtomodels.EmployeeDTO;
import com.epam.mentoring_p1.dtomodels.ProjectDTO;
import com.epam.mentoring_p1.employees.EmployeeRepository;
import com.epam.mentoring_p1.exceptions.EmployeeNotFoundException;
import com.epam.mentoring_p1.exceptions.ProjectNotFoundException;
import com.epam.mentoring_p1.repomodels.Employee;
import com.epam.mentoring_p1.repomodels.Project;
import com.epam.mentoring_p1.projects.ProjectRepository;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper mapper;

    public ProjectStaffOperationsImpl(ProjectRepository projectRepository, EmployeeRepository employeeRepository, ModelMapper mapper) {
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    @Override
    public Set<EmployeeDTO> getDeveloperList(Long projectId) {
        Optional<Project> projectFromDB = getProjectFromDB(projectId);
        Project project = projectFromDB.orElseThrow(getProjectNotFoundException(projectId));
        return mapper.map(project, ProjectDTO.class)
                .getDevelopers()
                .stream()
                .map(ContractDTO::getEmployee)
                .map(employee -> mapper.map(employee, EmployeeDTO.class))
                .collect(Collectors.toSet());
    }

    @Override
    public ProjectDTO addDeveloperToProject(Long projectId, Long employeeId, LocalDate endDate) {
        Project project = getProject(projectId);
        Employee employee = getEmployee(employeeId);
        project.addDeveloper(employee, endDate);
        Project saved = projectRepository.save(project);
        return mapper.map(saved, ProjectDTO.class);
    }

    @Override
    public ProjectDTO removeDeveloperFromProject(Long projectId, Long employeeId) {
        Project project = getProject(projectId);
        Employee employee = getEmployee(employeeId);
        project.removeDeveloper(employee);
        Project saved = projectRepository.save(project);
        return mapper.map(saved, ProjectDTO.class);
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
