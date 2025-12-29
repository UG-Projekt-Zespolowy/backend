package universityproject.taskmanager.user.dto;

import jakarta.validation.constraints.Size;

public record UpdateUserRequest(
        @Size(max = 100) String name,

        @Size(max = 100) String keycloakId,

        @Size(min = 3, max = 50) String username) {}
