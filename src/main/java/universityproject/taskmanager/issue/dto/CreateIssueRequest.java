package universityproject.taskmanager.issue.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.util.UUID;

public record CreateIssueRequest(
        @NotBlank(message = "Title must not be blank") @Size(max = 200) String title,

        @Size(max = 2000) String description,

        @PositiveOrZero(message = "Story point must be zero or positive") Integer storyPoint,

        @NotNull(message = "Reporter id must not be null") UUID reporterId,
        UUID assigneeId,
        UUID epicId) {}
