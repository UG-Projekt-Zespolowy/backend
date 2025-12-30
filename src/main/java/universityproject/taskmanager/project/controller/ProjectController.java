package universityproject.taskmanager.project.controller;

import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import universityproject.taskmanager.project.dto.CreateProjectRequest;
import universityproject.taskmanager.project.dto.ProjectResponse;
import universityproject.taskmanager.project.dto.UpdateProjectRequest;
import universityproject.taskmanager.project.mapper.ProjectMapper;
import universityproject.taskmanager.project.model.Project;
import universityproject.taskmanager.project.service.ProjectService;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@Valid @RequestBody CreateProjectRequest request) {

        Project project = projectService.createProject(request.name(), request.description(), request.ownerId());

        return ResponseEntity.status(HttpStatus.CREATED).body(ProjectMapper.toResponse(project));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable UUID id) {
        return ResponseEntity.ok(ProjectMapper.toResponse(projectService.getProjectById(id)));
    }

    @GetMapping
    public ResponseEntity<Page<ProjectResponse>> getAllProjects(Pageable pageable) {
        return ResponseEntity.ok(projectService.getAllProjects(pageable).map(ProjectMapper::toResponse));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<ProjectResponse>> getUserProjects(@PathVariable UUID userId, Pageable pageable) {

        return ResponseEntity.ok(
                projectService.getUserProjects(userId, pageable).map(ProjectMapper::toResponse));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProject(
            @PathVariable UUID id, @Valid @RequestBody UpdateProjectRequest request) {

        return ResponseEntity.ok(
                ProjectMapper.toResponse(projectService.updateProject(id, request.name(), request.description())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable UUID id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
