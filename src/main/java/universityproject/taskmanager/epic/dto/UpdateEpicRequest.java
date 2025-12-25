package universityproject.taskmanager.epic.dto;

public record UpdateEpicRequest(
    String title,
    String description
) {}