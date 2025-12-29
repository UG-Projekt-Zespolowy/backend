package universityproject.taskmanager.issue.controller;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import universityproject.taskmanager.issue.dto.AssignIssueRequest;
import universityproject.taskmanager.issue.dto.CreateIssueRequest;
import universityproject.taskmanager.issue.dto.UpdateIssueRequest;
import universityproject.taskmanager.issue.dto.UpdateIssueStatusRequest;
import universityproject.taskmanager.issue.model.Issue;
import universityproject.taskmanager.issue.service.IssueService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/issues")
public class IssueController {

    private final IssueService issueService;

    @PostMapping
    public ResponseEntity<Issue> createIssue(@Valid @RequestBody CreateIssueRequest request) {
        Issue issue = issueService.createIssue(
                request.title(),
                request.description(),
                request.storyPoint(),
                request.reporterId(),
                request.assigneeId(),
                request.epicId());
        return ResponseEntity.status(HttpStatus.CREATED).body(issue);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Issue> getIssueById(@PathVariable UUID id) {
        return ResponseEntity.ok(issueService.getIssueById(id));
    }

    @GetMapping
    public ResponseEntity<List<Issue>> getAllIssues() {
        return ResponseEntity.ok(issueService.getAllIssues());
    }

    @GetMapping("/epic/{epicId}")
    public ResponseEntity<List<Issue>> getEpicIssues(@PathVariable UUID epicId) {
        return ResponseEntity.ok(issueService.getEpicIssues(epicId));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Issue>> getProjectIssues(@PathVariable UUID projectId) {
        return ResponseEntity.ok(issueService.getProjectIssues(projectId));
    }

    @GetMapping("/assignee/{userId}")
    public ResponseEntity<List<Issue>> getUserAssignedIssues(@PathVariable UUID userId) {
        return ResponseEntity.ok(issueService.getUserAssignedIssues(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Issue> updateIssue(@PathVariable UUID id, @Valid @RequestBody UpdateIssueRequest request) {
        Issue issue = issueService.updateIssue(
                id,
                request.title(),
                request.description(),
                request.storyPoint(),
                request.status(),
                request.assigneeId());
        return ResponseEntity.ok(issue);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Issue> updateIssueStatus(
            @PathVariable UUID id, @Valid @RequestBody UpdateIssueStatusRequest request) {
        return ResponseEntity.ok(issueService.updateIssueStatus(id, request.status()));
    }

    @PatchMapping("/{id}/assign")
    public ResponseEntity<Issue> assignIssue(@PathVariable UUID id, @Valid @RequestBody AssignIssueRequest request) {
        return ResponseEntity.ok(issueService.assignIssue(id, request.assigneeId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIssue(@PathVariable UUID id) {
        issueService.deleteIssue(id);
        return ResponseEntity.noContent().build();
    }
}
