package universityproject.taskmanager.issue.dto;

import jakarta.validation.constraints.NotNull;
import universityproject.taskmanager.issue.enums.IssueStatus;

public record UpdateIssueStatusRequest(
        @NotNull(message = "Issue status must not be null") IssueStatus status) {}
