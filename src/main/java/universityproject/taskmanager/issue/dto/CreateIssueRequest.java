package universityproject.taskmanager.issue.dto;

import java.util.UUID;
public record CreateIssueRequest(
    String title,
    String description,
    Integer storyPoint,
    UUID reporterId,
    UUID assigneeId,
    UUID epicId
){}
