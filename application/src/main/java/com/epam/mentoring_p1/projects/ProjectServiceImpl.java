package com.epam.mentoring_p1.projects;

import com.epam.mentoring_p1.models.Project;
import com.epam.mentoring_p1.exceptions.ProjectNotFoundException;
import com.epam.mentoring_p1.exceptions.ProjectSavingException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Supplier;

@Service
public class ProjectServiceImpl implements ProjectService {

    private static final String PROJECT_CANNOT_BE_FOUND = "Project cannot be found: ";
    private static final String PROJECT_CANNOT_BE_SAVED = "Project cannot be saved";

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public ResponseEntity<Project> addProject(Project project) {
        Project saved = projectRepository.save(project);
        return createResponse(Optional.of(saved).orElseThrow(getProjectSavingException()));
    }

    @Override
    public ResponseEntity<Project> getProject(Long projectId) {
        Optional<Project> project = projectRepository.findById(projectId);
        return createResponse(project.orElseThrow(getProjectNotFoundException(projectId)));
    }

    @Override
    public ResponseEntity<Project> removeProject(Long projectId) {
        Optional<Project> tempEmployee = projectRepository.findById(projectId);
        Project projectToDelete = tempEmployee.orElseThrow(getProjectNotFoundException(projectId));
        projectRepository.delete(projectToDelete);
        return createResponse(projectToDelete);
    }

    private ResponseEntity<Project> createResponse(Project project) {
        return ResponseEntity.ok(project);
    }

    private Supplier<ProjectNotFoundException> getProjectNotFoundException(Long projectId) {
        return () -> new ProjectNotFoundException(PROJECT_CANNOT_BE_FOUND + projectId);
    }

    private Supplier<ProjectSavingException> getProjectSavingException() {
        return () -> new ProjectSavingException(PROJECT_CANNOT_BE_SAVED);
    }
}
