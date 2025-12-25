package universityproject.taskmanager.issue.dto;

import universityproject.taskmanager.issue.enums.IssueStatus;
import java.util.UUID;

public record UpdateIssueRequest(
    String title,
    String description,
    Integer storyPoint,
    IssueStatus status,
    UUID assigneeId
) {}
