package universityproject.taskmanager.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;

public record CreateProjectRequest(
        @NotBlank(message = "Project name must not be blank") @Size(max = 100) String name,
        @Size(max = 1000) String description,
        @NotNull(message = "Owner id must not be null") UUID ownerId) {}
