package universityproject.taskmanager.userproject.controller;

import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import universityproject.taskmanager.userproject.dto.AddUserToProjectRequest;
import universityproject.taskmanager.userproject.dto.UpdateUserRoleRequest;
import universityproject.taskmanager.userproject.dto.UserProjectResponse;
import universityproject.taskmanager.userproject.mapper.UserProjectMapper;
import universityproject.taskmanager.userproject.service.UserProjectService;

@RestController
@RequestMapping("/api/v1/user-projects")
@RequiredArgsConstructor
public class UserProjectController {

    private final UserProjectService userProjectService;

    @PostMapping("/projects/{projectId}/users")
    public ResponseEntity<UserProjectResponse> addUserToProject(
            @PathVariable UUID projectId, @Valid @RequestBody AddUserToProjectRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UserProjectMapper.toResponse(
                        userProjectService.addUserToProject(request.userId(), projectId, request.role(), false)));
    }

    @GetMapping("/projects/{projectId}/users")
    public ResponseEntity<Page<UserProjectResponse>> getProjectMembers(
            @PathVariable UUID projectId, Pageable pageable) {

        return ResponseEntity.ok(
                userProjectService.getProjectMembers(projectId, pageable).map(UserProjectMapper::toResponse));
    }

    @GetMapping("/users/{userId}/projects")
    public ResponseEntity<Page<UserProjectResponse>> getUserProjects(@PathVariable UUID userId, Pageable pageable) {

        return ResponseEntity.ok(
                userProjectService.getUserProjects(userId, pageable).map(UserProjectMapper::toResponse));
    }

    @GetMapping("/projects/{projectId}/owner")
    public ResponseEntity<UserProjectResponse> getProjectOwner(@PathVariable UUID projectId) {
        return ResponseEntity.ok(UserProjectMapper.toResponse(userProjectService.getProjectOwner(projectId)));
    }

    @PatchMapping("/projects/{projectId}/users/{userId}/role")
    public ResponseEntity<UserProjectResponse> updateUserRole(
            @PathVariable UUID projectId,
            @PathVariable UUID userId,
            @Valid @RequestBody UpdateUserRoleRequest request) {

        return ResponseEntity.ok(
                UserProjectMapper.toResponse(userProjectService.updateUserRole(userId, projectId, request.role())));
    }

    @DeleteMapping("/projects/{projectId}/users/{userId}")
    public ResponseEntity<Void> removeUserFromProject(@PathVariable UUID projectId, @PathVariable UUID userId) {

        userProjectService.removeUserFromProject(userId, projectId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/projects/{projectId}/users/{userId}/is-member")
    public ResponseEntity<Boolean> isUserInProject(@PathVariable UUID projectId, @PathVariable UUID userId) {

        return ResponseEntity.ok(userProjectService.isUserInProject(userId, projectId));
    }

    @GetMapping("/projects/{projectId}/users/{userId}/is-owner")
    public ResponseEntity<Boolean> isUserOwner(@PathVariable UUID projectId, @PathVariable UUID userId) {

        return ResponseEntity.ok(userProjectService.isUserOwner(userId, projectId));
    }
}
