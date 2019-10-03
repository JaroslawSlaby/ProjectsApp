package com.epam.mentoring_p1.projects;

import com.epam.mentoring_p1.dtomodels.ProjectDTO;
import org.springframework.http.ResponseEntity;

public interface ProjectService {
    ResponseEntity<ProjectDTO> addProject(ProjectDTO project);

    ResponseEntity<ProjectDTO> getProject(Long projectId);

    ResponseEntity<ProjectDTO> removeProject(Long projectId);
}