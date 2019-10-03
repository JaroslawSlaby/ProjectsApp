package com.epam.mentoring_p1.projects;

import com.epam.mentoring_p1.dtomodels.ProjectDTO;
import com.epam.mentoring_p1.repomodels.Project;
import com.epam.mentoring_p1.exceptions.ProjectNotFoundException;
import com.epam.mentoring_p1.exceptions.ProjectSavingException;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Supplier;

@Service
public class ProjectServiceImpl implements ProjectService {

    private static final String PROJECT_CANNOT_BE_FOUND = "Project cannot be found: ";
    private static final String PROJECT_CANNOT_BE_SAVED = "Project cannot be saved";

    private final ProjectRepository projectRepository;
    private final ModelMapper mapper;

    public ProjectServiceImpl(ProjectRepository projectRepository, ModelMapper mapper) {
        this.projectRepository = projectRepository;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<ProjectDTO> addProject(ProjectDTO project) {
        Project toBeSaved = mapper.map(project, Project.class);
        Project saved = projectRepository.save(toBeSaved);
        ProjectDTO dto = mapper.map(saved, ProjectDTO.class);
        return createResponse(Optional.of(dto).orElseThrow(getProjectSavingException()));
    }

    @Override
    public ResponseEntity<ProjectDTO> getProject(Long projectId) {
        Optional<Project> projectFromDB = projectRepository.findById(projectId);
        Project project = projectFromDB.orElseThrow(getProjectNotFoundException(projectId));
        return createResponse(mapper.map(project, ProjectDTO.class));
    }

    @Override
    public ResponseEntity<ProjectDTO> removeProject(Long projectId) {
        Optional<Project> tempEmployee = projectRepository.findById(projectId);
        Project projectToDelete = tempEmployee.orElseThrow(getProjectNotFoundException(projectId));
        projectRepository.delete(projectToDelete);
        return createResponse(mapper.map(projectToDelete, ProjectDTO.class));
    }

    private ResponseEntity<ProjectDTO> createResponse(ProjectDTO project) {
        return ResponseEntity.ok(project);
    }

    private Supplier<ProjectNotFoundException> getProjectNotFoundException(Long projectId) {
        return () -> new ProjectNotFoundException(PROJECT_CANNOT_BE_FOUND + projectId);
    }

    private Supplier<ProjectSavingException> getProjectSavingException() {
        return () -> new ProjectSavingException(PROJECT_CANNOT_BE_SAVED);
    }
}
