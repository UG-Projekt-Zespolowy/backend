package universityproject.taskmanager.issue.mapper;

import static java.util.Objects.nonNull;

import lombok.experimental.UtilityClass;
import universityproject.taskmanager.issue.dto.IssueResponse;
import universityproject.taskmanager.issue.model.Issue;

@UtilityClass
public final class IssueMapper {
    public static IssueResponse toResponse(Issue issue) {
        return new IssueResponse(
                issue.getId(),
                issue.getTitle(),
                issue.getDescription(),
                issue.getStoryPoint(),
                issue.getStatus(),
                issue.getReporter().getId(),
                nonNull(issue.getAssignee()) ? issue.getAssignee().getId() : null,
                nonNull(issue.getEpic()) ? issue.getEpic().getId() : null,
                issue.getCreatedAt());
    }
}
