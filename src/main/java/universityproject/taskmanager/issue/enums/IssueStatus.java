package universityproject.taskmanager.issue.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IssueStatus {
    TO_DO("To Do"),
    IN_PROGRESS("In Progress"),
    READY_FOR_REVIEW("Ready for Review"),
    DONE("Done"),
    CLOSED("Closed"),
    BACKLOG("Backlog");

    private final String displayName;
}
