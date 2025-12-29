package universityproject.taskmanager.userproject.service;

import java.util.List;
import java.util.UUID;
import universityproject.taskmanager.project.enums.ProjectRole;
import universityproject.taskmanager.userproject.model.UserProject;

public interface UserProjectService {

    UserProject addUserToProject(UUID userId, UUID projectId, ProjectRole role, Boolean isOwner);

    UserProject updateUserRole(UUID userId, UUID projectId, ProjectRole role);

    void removeUserFromProject(UUID userId, UUID projectId);

    List<UserProject> getProjectMembers(UUID projectId);

    List<UserProject> getUserProjects(UUID userId);

    UserProject getProjectOwner(UUID projectId);

    boolean isUserInProject(UUID userId, UUID projectId);

    boolean isUserOwner(UUID userId, UUID projectId);
}
