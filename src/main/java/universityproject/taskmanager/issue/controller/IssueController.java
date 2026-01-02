package universityproject.taskmanager.issue.controller;

import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import universityproject.taskmanager.issue.dto.*;
import universityproject.taskmanager.issue.mapper.IssueMapper;
import universityproject.taskmanager.issue.model.Issue;
import universityproject.taskmanager.issue.service.IssueService;

@RestController
@RequestMapping("/api/v1/issues")
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;

    @PostMapping
    public ResponseEntity<IssueResponse> createIssue(@Valid @RequestBody CreateIssueRequest request) {
        Issue issue = issueService.createIssue(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(IssueMapper.toResponse(issue));
    }

    @GetMapping("/{id}")
    public ResponseEntity<IssueResponse> getIssueById(@PathVariable UUID id) {
        return ResponseEntity.ok(IssueMapper.toResponse(issueService.getIssueById(id)));
    }

    @GetMapping
    public ResponseEntity<Page<IssueResponse>> getAllIssues(Pageable pageable) {
        return ResponseEntity.ok(issueService.getAllIssues(pageable).map(IssueMapper::toResponse));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<Page<IssueResponse>> getProjectIssues(@PathVariable UUID projectId, Pageable pageable) {
        return ResponseEntity.ok(
                issueService.getProjectIssues(projectId, pageable).map(IssueMapper::toResponse));
    }

    @GetMapping("/epic/{epicId}")
    public ResponseEntity<Page<IssueResponse>> getEpicIssues(@PathVariable UUID epicId, Pageable pageable) {
        return ResponseEntity.ok(issueService.getEpicIssues(epicId, pageable).map(IssueMapper::toResponse));
    }

    @GetMapping("/assignee/{userId}")
    public ResponseEntity<Page<IssueResponse>> getUserAssignedIssues(@PathVariable UUID userId, Pageable pageable) {
        return ResponseEntity.ok(
                issueService.getUserAssignedIssues(userId, pageable).map(IssueMapper::toResponse));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IssueResponse> updateIssue(
            @PathVariable UUID id, @Valid @RequestBody UpdateIssueRequest request) {
        return ResponseEntity.ok(IssueMapper.toResponse(issueService.updateIssue(id, request)));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<IssueResponse> updateStatus(
            @PathVariable UUID id, @Valid @RequestBody UpdateIssueStatusRequest request) {
        return ResponseEntity.ok(IssueMapper.toResponse(issueService.updateIssueStatus(id, request.status())));
    }

    @PatchMapping("/{id}/assign")
    public ResponseEntity<IssueResponse> assignIssue(
            @PathVariable UUID id, @Valid @RequestBody AssignIssueRequest request) {
        return ResponseEntity.ok(IssueMapper.toResponse(issueService.assignIssue(id, request.assigneeId())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIssue(@PathVariable UUID id) {
        issueService.deleteIssue(id);
        return ResponseEntity.noContent().build();
    }
}
