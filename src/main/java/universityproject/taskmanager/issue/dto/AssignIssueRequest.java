package universityproject.taskmanager.issue.dto;

import java.util.UUID;
public record AssignIssueRequest(
    UUID assigneeId
){}