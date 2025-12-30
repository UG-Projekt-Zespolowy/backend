package universityproject.taskmanager.issue.mapper;

import universityproject.taskmanager.issue.dto.IssueResponse;
import universityproject.taskmanager.issue.model.Issue;

public class IssueMapper {
    public static IssueResponse toResponse(Issue issue) {
        return new IssueResponse(
                issue.getId(),
                issue.getTitle(),
                issue.getDescription(),
                issue.getStoryPoint(),
                issue.getStatus(),
                issue.getReporter().getId(),
                issue.getAssignee() != null ? issue.getAssignee().getId() : null,
                issue.getEpic() != null ? issue.getEpic().getId() : null,
                issue.getCreatedAt());
    }
}
