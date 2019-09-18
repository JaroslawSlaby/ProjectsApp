package com.epam.mentoring_p1.projects;

import com.epam.mentoring_p1.models.Project;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/projects/{projectId}")
    ResponseEntity<Project> getProject(@PathVariable(value = "projectId") Long projectId) {
        return projectService.getProject(projectId);
    }

    @PostMapping("/projects")
    ResponseEntity<Project> addProject(@RequestBody @Valid Project project) {
        return projectService.addProject(project);
    }

    @DeleteMapping("/projects/{projectId}")
    ResponseEntity<Project> removeProject(@PathVariable(value = "projectId") Long projectId) {
        return projectService.removeProject(projectId);
    }
}
