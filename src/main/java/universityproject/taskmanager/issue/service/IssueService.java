package universityproject.taskmanager.issue.service;

import java.util.List;
import java.util.UUID;
import universityproject.taskmanager.issue.enums.IssueStatus;
import universityproject.taskmanager.issue.model.Issue;

public interface IssueService {
    Issue createIssue(
            String title, String description, Integer storyPoint, UUID reporterId, UUID assigneeId, UUID epicId);

    Issue updateIssue(
            UUID issueId, String title, String description, Integer storyPoint, IssueStatus status, UUID assigneeId);

    Issue updateIssueStatus(UUID issueId, IssueStatus status);

    Issue assignIssue(UUID issueId, UUID assigneeId);

    void deleteIssue(UUID issueId);

    Issue getIssueById(UUID issueId);

    List<Issue> getEpicIssues(UUID epicId);

    List<Issue> getProjectIssues(UUID projectId);

    List<Issue> getUserAssignedIssues(UUID userId);

    List<Issue> getUserReportedIssues(UUID userId);

    List<Issue> getIssuesByStatus(IssueStatus status);

    List<Issue> getAllIssues();
}
