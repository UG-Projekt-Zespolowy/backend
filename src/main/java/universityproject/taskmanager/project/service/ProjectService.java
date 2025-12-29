package universityproject.taskmanager.project.service;

import java.util.List;
import java.util.UUID;
import universityproject.taskmanager.project.model.Project;

public interface ProjectService {

    Project createProject(String name, String description, UUID ownerId);

    Project updateProject(UUID projectId, String name, String description);

    void deleteProject(UUID projectId);

    Project getProjectById(UUID projectId);

    List<Project> getAllProjects();

    List<Project> getUserProjects(UUID userId);
}
