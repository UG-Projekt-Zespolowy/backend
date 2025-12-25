package universityproject.taskmanager.issue.dto;

import universityproject.taskmanager.issue.enums.IssueStatus;

public record UpdateIssueStatusRequest (
    IssueStatus status
){}
