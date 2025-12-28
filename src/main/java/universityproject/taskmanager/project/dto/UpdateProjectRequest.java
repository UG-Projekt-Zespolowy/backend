package universityproject.taskmanager.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateProjectRequest(
        @NotBlank(message = "Project name must not be blank") @Size(max = 100) String name,

        @Size(max = 1000) String description) {}
