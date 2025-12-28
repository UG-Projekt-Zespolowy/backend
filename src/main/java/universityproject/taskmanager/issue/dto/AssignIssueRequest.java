package universityproject.taskmanager.issue.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record AssignIssueRequest(
        @NotNull(message = "Assignee id must not be null") UUID assigneeId) {}
