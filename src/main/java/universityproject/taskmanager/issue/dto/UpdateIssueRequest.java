package universityproject.taskmanager.issue.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import universityproject.taskmanager.issue.enums.IssueStatus;

public record UpdateIssueRequest(
        @NotBlank(message = "Title must not be blank") @Size(max = 200) String title,
        @Size(max = 2000) String description,
        @PositiveOrZero(message = "Story point must be zero or positive") Integer storyPoint,
        @NotNull(message = "Issue status must not be null") IssueStatus status,
        UUID assigneeId) {}
