package com.epam.mentoring_p1.projects;

import com.epam.mentoring_p1.models.Project;
import org.springframework.http.ResponseEntity;

public interface ProjectService {
    ResponseEntity<Project> addProject(Project project);

    ResponseEntity<Project> getProject(Long projectId);

    ResponseEntity<Project> removeProject(Long projectId);
}