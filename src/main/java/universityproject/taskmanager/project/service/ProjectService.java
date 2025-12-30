package universityproject.taskmanager.project.service;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import universityproject.taskmanager.project.model.Project;

public interface ProjectService {

    Project createProject(String name, String description, UUID ownerId);

    Project updateProject(UUID projectId, String name, String description);

    void deleteProject(UUID projectId);

    Project getProjectById(UUID projectId);

    Page<Project> getAllProjects(Pageable pageable);

    Page<Project> getUserProjects(UUID userId, Pageable pageable);
}
