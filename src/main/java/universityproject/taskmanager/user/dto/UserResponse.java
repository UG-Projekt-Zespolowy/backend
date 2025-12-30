package universityproject.taskmanager.user.dto;

import java.util.UUID;

public record UserResponse(UUID id, String keycloakId, String name, String username) {}
