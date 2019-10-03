package com.epam.mentoring_p1.projects;

import com.epam.mentoring_p1.dtomodels.ProjectDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ProjectController {

    private final ProjectService projectService;

    ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/projects/{projectId}")
    ResponseEntity<ProjectDTO> getProject(@PathVariable(value = "projectId") Long projectId) {
        return projectService.getProject(projectId);
    }

    @PostMapping("/projects")
    ResponseEntity<ProjectDTO> addProject(@RequestBody @Valid ProjectDTO project) {
        return projectService.addProject(project);
    }

    @DeleteMapping("/projects/{projectId}")
    ResponseEntity<ProjectDTO> removeProject(@PathVariable(value = "projectId") Long projectId) {
        return projectService.removeProject(projectId);
    }
}
