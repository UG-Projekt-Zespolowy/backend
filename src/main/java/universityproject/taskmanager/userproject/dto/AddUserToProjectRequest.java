package universityproject.taskmanager.userproject.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import universityproject.taskmanager.project.enums.ProjectRole;

public record AddUserToProjectRequest(
        @NotNull(message = "User id must not be null") UUID userId,
        @NotNull(message = "Project role must not be null") ProjectRole role) {}
