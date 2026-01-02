package universityproject.taskmanager.issue.service;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import universityproject.taskmanager.issue.dto.CreateIssueRequest;
import universityproject.taskmanager.issue.dto.UpdateIssueRequest;
import universityproject.taskmanager.issue.enums.IssueStatus;
import universityproject.taskmanager.issue.model.Issue;

public interface IssueService {
    Issue createIssue(CreateIssueRequest request);

    Issue updateIssue(UUID issueId, UpdateIssueRequest request);

    Issue updateIssueStatus(UUID issueId, IssueStatus status);

    Issue assignIssue(UUID issueId, UUID assigneeId);

    void deleteIssue(UUID issueId);

    Issue getIssueById(UUID issueId);

    Page<Issue> getAllIssues(Pageable pageable);

    Page<Issue> getProjectIssues(UUID projectId, Pageable pageable);

    Page<Issue> getEpicIssues(UUID epicId, Pageable pageable);

    Page<Issue> getUserAssignedIssues(UUID userId, Pageable pageable);
}
