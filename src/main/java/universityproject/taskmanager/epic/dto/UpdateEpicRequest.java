package universityproject.taskmanager.epic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateEpicRequest(
        @NotBlank(message = "Title must not be blank") @Size(max = 200) String title,
        @Size(max = 2000) String description) {}
