package universityproject.taskmanager.userproject.dto;

import jakarta.validation.constraints.NotNull;
import universityproject.taskmanager.project.enums.ProjectRole;

public record UpdateUserRoleRequest(
        @NotNull(message = "Project role must not be null") ProjectRole role) {}
