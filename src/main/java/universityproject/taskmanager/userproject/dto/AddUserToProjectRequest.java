package universityproject.taskmanager.userproject.dto;

import universityproject.taskmanager.project.enums.ProjectRole;

import java.util.UUID;

public record AddUserToProjectRequest (
    UUID userId,
    ProjectRole role
) {}
