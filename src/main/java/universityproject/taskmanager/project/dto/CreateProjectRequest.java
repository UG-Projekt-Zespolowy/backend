package universityproject.taskmanager.project.dto;

import java.util.UUID;

public record CreateProjectRequest (
    String name,
    String description,
    UUID ownerId
){}