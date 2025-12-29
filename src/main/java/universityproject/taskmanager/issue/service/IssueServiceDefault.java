package universityproject.taskmanager.issue.service;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import universityproject.taskmanager.epic.model.Epic;
import universityproject.taskmanager.epic.repository.EpicRepository;
import universityproject.taskmanager.issue.enums.IssueStatus;
import universityproject.taskmanager.issue.model.Issue;
import universityproject.taskmanager.issue.repository.IssueRepository;
import universityproject.taskmanager.project.repository.ProjectRepository;
import universityproject.taskmanager.user.model.User;
import universityproject.taskmanager.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class IssueServiceDefault implements IssueService {

    private final IssueRepository issueRepository;
    private final UserRepository userRepository;
    private final EpicRepository epicRepository;
    private final ProjectRepository projectRepository;

    @Override
    @Transactional
    public Issue createIssue(
            String title, String description, Integer storyPoint, UUID reporterId, UUID assigneeId, UUID epicId) {
        User reporter = userRepository
                .findById(reporterId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reporter not found"));

        Issue issue = Issue.builder()
                .title(title)
                .description(description)
                .storyPoint(storyPoint)
                .status(IssueStatus.TO_DO)
                .reporter(reporter)
                .build();

        if (assigneeId != null) {
            User assignee = userRepository
                    .findById(assigneeId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Assignee not found"));
            issue.setAssignee(assignee);
        }

        if (epicId != null) {
            Epic epic = epicRepository
                    .findById(epicId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Epic not found"));
            issue.setEpic(epic);
        }

        return issueRepository.save(issue);
    }

    @Override
    @Transactional
    public Issue updateIssue(
            UUID issueId, String title, String description, Integer storyPoint, IssueStatus status, UUID assigneeId) {
        Issue issue = issueRepository
                .findById(issueId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Issue not found"));

        issue.setTitle(title);
        issue.setDescription(description);
        issue.setStoryPoint(storyPoint);
        issue.setStatus(status);

        if (assigneeId != null) {
            User assignee = userRepository
                    .findById(assigneeId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Assignee not found"));
            issue.setAssignee(assignee);
        } else {
            issue.setAssignee(null);
        }

        return issueRepository.save(issue);
    }

    @Override
    @Transactional
    public Issue updateIssueStatus(UUID issueId, IssueStatus status) {
        Issue issue = issueRepository
                .findById(issueId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Issue not found"));
        issue.setStatus(status);
        return issueRepository.save(issue);
    }

    @Override
    @Transactional
    public Issue assignIssue(UUID issueId, UUID assigneeId) {
        Issue issue = issueRepository
                .findById(issueId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Issue not found"));

        if (assigneeId != null) {
            User assignee = userRepository
                    .findById(assigneeId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Assignee not found"));
            issue.setAssignee(assignee);
        } else {
            issue.setAssignee(null);
        }

        return issueRepository.save(issue);
    }

    @Override
    @Transactional
    public void deleteIssue(UUID issueId) {
        if (!issueRepository.existsById(issueId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Issue not found");
        }
        issueRepository.deleteById(issueId);
    }

    @Override
    public Issue getIssueById(UUID issueId) {
        return issueRepository
                .findById(issueId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Issue not found"));
    }

    @Override
    public List<Issue> getEpicIssues(UUID epicId) {
        if (!epicRepository.existsById(epicId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Epic not found");
        }
        return issueRepository.findByEpicId(epicId);
    }

    @Override
    public List<Issue> getProjectIssues(UUID projectId) {
        if (!projectRepository.existsById(projectId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found");
        }
        return issueRepository.findByProjectId(projectId);
    }

    @Override
    public List<Issue> getUserAssignedIssues(UUID userId) {
        return issueRepository.findByAssigneeId(userId);
    }

    @Override
    public List<Issue> getUserReportedIssues(UUID userId) {
        return issueRepository.findByReporterId(userId);
    }

    @Override
    public List<Issue> getIssuesByStatus(IssueStatus status) {
        return issueRepository.findByStatus(status);
    }

    @Override
    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }
}
