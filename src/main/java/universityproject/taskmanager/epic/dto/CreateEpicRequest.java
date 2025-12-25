package universityproject.taskmanager.epic.dto;

import java.util.UUID;

public record CreateEpicRequest(
    String title,
    String description,
    UUID projectId
) {}
