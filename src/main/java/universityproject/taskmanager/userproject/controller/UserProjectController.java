package universityproject.taskmanager.userproject.controller;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import universityproject.taskmanager.userproject.dto.AddUserToProjectRequest;
import universityproject.taskmanager.userproject.dto.UpdateUserRoleRequest;
import universityproject.taskmanager.userproject.model.UserProject;
import universityproject.taskmanager.userproject.service.UserProjectService;

@RestController
@RequestMapping("/api/v1/user-projects")
@RequiredArgsConstructor
public class UserProjectController {

    private final UserProjectService userProjectService;

    @PostMapping("/projects/{projectId}/users")
    public ResponseEntity<UserProject> addUserToProject(
            @PathVariable UUID projectId, @Valid @RequestBody AddUserToProjectRequest request) {
        UserProject userProject =
                userProjectService.addUserToProject(request.userId(), projectId, request.role(), false);
        return ResponseEntity.status(HttpStatus.CREATED).body(userProject);
    }

    @GetMapping("/projects/{projectId}/users")
    public ResponseEntity<List<UserProject>> getProjectMembers(@PathVariable UUID projectId) {
        List<UserProject> members = userProjectService.getProjectMembers(projectId);
        return ResponseEntity.ok(members);
    }

    @GetMapping("/users/{userId}/projects")
    public ResponseEntity<List<UserProject>> getUserProjects(@PathVariable UUID userId) {
        List<UserProject> projects = userProjectService.getUserProjects(userId);
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/projects/{projectId}/owner")
    public ResponseEntity<UserProject> getProjectOwner(@PathVariable UUID projectId) {
        UserProject owner = userProjectService.getProjectOwner(projectId);
        return ResponseEntity.ok(owner);
    }

    @PatchMapping("/projects/{projectId}/users/{userId}/role")
    public ResponseEntity<UserProject> updateUserRole(
            @PathVariable UUID projectId,
            @PathVariable UUID userId,
            @Valid @RequestBody UpdateUserRoleRequest request) {
        UserProject userProject = userProjectService.updateUserRole(userId, projectId, request.role());
        return ResponseEntity.ok(userProject);
    }

    @DeleteMapping("/projects/{projectId}/users/{userId}")
    public ResponseEntity<Void> removeUserFromProject(@PathVariable UUID projectId, @PathVariable UUID userId) {
        userProjectService.removeUserFromProject(userId, projectId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/projects/{projectId}/users/{userId}/is-member")
    public ResponseEntity<Boolean> isUserInProject(@PathVariable UUID projectId, @PathVariable UUID userId) {
        boolean isMember = userProjectService.isUserInProject(userId, projectId);
        return ResponseEntity.ok(isMember);
    }

    @GetMapping("/projects/{projectId}/users/{userId}/is-owner")
    public ResponseEntity<Boolean> isUserOwner(@PathVariable UUID projectId, @PathVariable UUID userId) {
        boolean isOwner = userProjectService.isUserOwner(userId, projectId);
        return ResponseEntity.ok(isOwner);
    }
}
