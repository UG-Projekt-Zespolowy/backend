package universityproject.taskmanager.issue.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import universityproject.taskmanager.epic.repository.EpicRepository;
import universityproject.taskmanager.exception.IssueNotFoundException;
import universityproject.taskmanager.exception.ResourceNotFoundException;
import universityproject.taskmanager.issue.dto.CreateIssueRequest;
import universityproject.taskmanager.issue.dto.UpdateIssueRequest;
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
    public Issue createIssue(CreateIssueRequest request) {
        User reporter = userRepository
                .findById(request.reporterId())
                .orElseThrow(() -> new ResourceNotFoundException("Reporter not found"));

        Issue issue = Issue.builder()
                .title(request.title())
                .description(request.description())
                .storyPoint(request.storyPoint())
                .status(IssueStatus.TO_DO)
                .reporter(reporter)
                .build();

        if (request.assigneeId() != null) {
            issue.setAssignee(userRepository.findById(request.assigneeId()).orElse(null));
        }

        if (request.epicId() != null) {
            issue.setEpic(epicRepository
                    .findById(request.epicId())
                    .orElseThrow(() -> new ResourceNotFoundException("Epic not found")));
        }

        return issueRepository.save(issue);
    }

    @Override
    @Transactional
    public Issue updateIssue(UUID issueId, UpdateIssueRequest request) {
        Issue issue = issueRepository.findById(issueId).orElseThrow(() -> new IssueNotFoundException(issueId));

        if (request.title() != null && !request.title().isBlank()) {
            issue.setTitle(request.title());
        }
        issue.setDescription(request.description());
        issue.setStoryPoint(request.storyPoint());
        issue.setStatus(request.status());

        if (request.assigneeId() != null) {
            User assignee = userRepository
                    .findById(request.assigneeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Assignee not found"));
            issue.setAssignee(assignee);
        } else {
            issue.setAssignee(null);
        }

        return issueRepository.save(issue);
    }

    @Override
    @Transactional
    public Issue updateIssueStatus(UUID issueId, IssueStatus status) {
        Issue issue = issueRepository.findById(issueId).orElseThrow(() -> new IssueNotFoundException(issueId));
        issue.setStatus(status);
        return issueRepository.save(issue);
    }

    @Override
    @Transactional
    public Issue assignIssue(UUID issueId, UUID assigneeId) {
        Issue issue = issueRepository.findById(issueId).orElseThrow(() -> new IssueNotFoundException(issueId));
        User assignee =
                userRepository.findById(assigneeId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        issue.setAssignee(assignee);
        return issueRepository.save(issue);
    }

    @Override
    @Transactional
    public void deleteIssue(UUID issueId) {
        Issue issue = issueRepository.findById(issueId).orElseThrow(() -> new IssueNotFoundException(issueId));
        issueRepository.delete(issue);
    }

    @Override
    public Issue getIssueById(UUID issueId) {
        return issueRepository.findById(issueId).orElseThrow(() -> new IssueNotFoundException(issueId));
    }

    @Override
    public Page<Issue> getAllIssues(Pageable pageable) {
        return issueRepository.findAll(pageable);
    }

    @Override
    public Page<Issue> getProjectIssues(UUID projectId, Pageable pageable) {
        if (!projectRepository.existsById(projectId)) {
            throw new ResourceNotFoundException("Project not found");
        }
        return issueRepository.findByProjectId(projectId, pageable);
    }

    @Override
    public Page<Issue> getEpicIssues(UUID epicId, Pageable pageable) {
        if (!epicRepository.existsById(epicId)) {
            throw new ResourceNotFoundException("Epic not found");
        }
        return issueRepository.findByEpicId(epicId, pageable);
    }

    @Override
    public Page<Issue> getUserAssignedIssues(UUID userId, Pageable pageable) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found");
        }
        return issueRepository.findByAssigneeId(userId, pageable);
    }
}
