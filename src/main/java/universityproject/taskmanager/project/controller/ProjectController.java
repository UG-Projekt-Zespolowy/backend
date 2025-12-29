package universityproject.taskmanager.project.controller;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import universityproject.taskmanager.project.dto.CreateProjectRequest;
import universityproject.taskmanager.project.dto.UpdateProjectRequest;
import universityproject.taskmanager.project.model.Project;
import universityproject.taskmanager.project.service.ProjectService;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<Project> createProject(@Valid @RequestBody CreateProjectRequest request) {
        Project project = projectService.createProject(request.name(), request.description(), request.ownerId());
        return ResponseEntity.status(HttpStatus.CREATED).body(project);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable UUID id) {
        Project project = projectService.getProjectById(id);
        return ResponseEntity.ok(project);
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Project>> getUserProjects(@PathVariable UUID userId) {
        List<Project> projects = projectService.getUserProjects(userId);
        return ResponseEntity.ok(projects);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(
            @PathVariable UUID id, @Valid @RequestBody UpdateProjectRequest request) {
        Project project = projectService.updateProject(id, request.name(), request.description());
        return ResponseEntity.ok(project);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable UUID id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
