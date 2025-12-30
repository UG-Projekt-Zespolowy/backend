package universityproject.taskmanager.project.mapper;

import universityproject.taskmanager.project.dto.ProjectResponse;
import universityproject.taskmanager.project.model.Project;

public class ProjectMapper {
    public static ProjectResponse toResponse(Project project) {
        return new ProjectResponse(
                project.getId(), project.getName(), project.getDescription(), project.getCreatedAt());
    }
}
