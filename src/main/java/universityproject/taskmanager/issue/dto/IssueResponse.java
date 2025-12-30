package universityproject.taskmanager.issue.dto;

import java.time.LocalDateTime;
import java.util.UUID;
import universityproject.taskmanager.issue.enums.IssueStatus;

public record IssueResponse(
        UUID id,
        String title,
        String description,
        Integer storyPoint,
        IssueStatus status,
        UUID reporterId,
        UUID assigneeId,
        UUID epicId,
        LocalDateTime createdAt) {}
