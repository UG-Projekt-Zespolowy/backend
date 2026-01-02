package universityproject.taskmanager.userproject.dto;

import java.time.LocalDateTime;
import java.util.UUID;
import universityproject.taskmanager.project.enums.ProjectRole;

public record UserProjectResponse(
        UUID id, UUID userId, UUID projectId, ProjectRole role, Boolean isOwner, LocalDateTime joinedAt) {}
