package universityproject.taskmanager.project.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProjectRole {
    PROJECT_MANAGER("Project Manager"),
    MEMBER("Member");

    private final String displayName;
}
