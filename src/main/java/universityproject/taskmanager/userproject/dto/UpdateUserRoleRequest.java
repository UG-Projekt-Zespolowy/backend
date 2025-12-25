package universityproject.taskmanager.userproject.dto;

import universityproject.taskmanager.project.enums.ProjectRole;

public record UpdateUserRoleRequest (
    ProjectRole role
) {}
