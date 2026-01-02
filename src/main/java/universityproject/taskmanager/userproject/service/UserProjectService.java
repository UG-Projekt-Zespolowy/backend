package universityproject.taskmanager.userproject.service;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import universityproject.taskmanager.project.enums.ProjectRole;
import universityproject.taskmanager.userproject.model.UserProject;

public interface UserProjectService {

    UserProject addUserToProject(UUID userId, UUID projectId, ProjectRole role, Boolean isOwner);

    UserProject updateUserRole(UUID userId, UUID projectId, ProjectRole role);

    void removeUserFromProject(UUID userId, UUID projectId);

    Page<UserProject> getProjectMembers(UUID projectId, Pageable pageable);

    Page<UserProject> getUserProjects(UUID userId, Pageable pageable);

    UserProject getProjectOwner(UUID projectId);

    boolean isUserInProject(UUID userId, UUID projectId);

    boolean isUserOwner(UUID userId, UUID projectId);
}
