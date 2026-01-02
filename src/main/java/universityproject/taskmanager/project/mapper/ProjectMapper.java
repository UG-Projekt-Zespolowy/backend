package universityproject.taskmanager.project.mapper;

import lombok.experimental.UtilityClass;
import universityproject.taskmanager.project.dto.ProjectResponse;
import universityproject.taskmanager.project.model.Project;

@UtilityClass
public final class ProjectMapper {
    public static ProjectResponse toResponse(Project project) {
        return new ProjectResponse(
                project.getId(), project.getName(), project.getDescription(), project.getCreatedAt());
    }
}
