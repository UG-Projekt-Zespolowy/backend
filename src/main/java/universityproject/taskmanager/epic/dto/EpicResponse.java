package universityproject.taskmanager.epic.dto;

import java.util.UUID;

public record EpicResponse(UUID id, String title, String description, UUID projectId) {}
