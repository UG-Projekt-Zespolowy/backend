package universityproject.taskmanager.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
        @NotBlank(message = "Name must not be blank") @Size(max = 100) String name,

        @NotBlank(message = "Keycloak ID must not be blank") @Size(max = 100) String keycloakId,

        @NotBlank(message = "Username must not be blank") @Size(min = 3, max = 50) String username) {}
