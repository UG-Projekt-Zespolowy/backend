package universityproject.taskmanager.epic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;

public record CreateEpicRequest(
        @NotBlank(message = "Title must not be blank") @Size(max = 200) String title,
        @Size(max = 2000) String description,
        @NotNull(message = "Project id must not be null") UUID projectId) {}
