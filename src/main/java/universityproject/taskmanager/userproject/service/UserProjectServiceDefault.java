package universityproject.taskmanager.userproject.service;

import static java.util.Objects.nonNull;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import universityproject.taskmanager.project.enums.ProjectRole;
import universityproject.taskmanager.project.model.Project;
import universityproject.taskmanager.project.repository.ProjectRepository;
import universityproject.taskmanager.user.model.User;
import universityproject.taskmanager.user.repository.UserRepository;
import universityproject.taskmanager.userproject.model.UserProject;
import universityproject.taskmanager.userproject.repository.UserProjectRepository;

@Service
@RequiredArgsConstructor
public class UserProjectServiceDefault implements UserProjectService {

    private final UserProjectRepository userProjectRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Override
    @Transactional
    public UserProject addUserToProject(UUID userId, UUID projectId, ProjectRole role, Boolean isOwner) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + userId + " not found"));

        Project project = projectRepository
                .findById(projectId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Project with id " + projectId + " not found"));

        if (userProjectRepository.existsByUserIdAndProjectId(userId, projectId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User is already a member of this project");
        }

        UserProject userProject = UserProject.builder()
                .user(user)
                .project(project)
                .role(role)
                .isOwner(nonNull(isOwner) && isOwner)
                .build();

        return userProjectRepository.save(userProject);
    }

    @Override
    @Transactional
    public UserProject updateUserRole(UUID userId, UUID projectId, ProjectRole role) {
        UserProject userProject = userProjectRepository
                .findByUserIdAndProjectId(userId, projectId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "User is not a member of this project"));

        userProject.setRole(role);
        return userProjectRepository.save(userProject);
    }

    @Override
    @Transactional
    public void removeUserFromProject(UUID userId, UUID projectId) {
        UserProject userProject = userProjectRepository
                .findByUserIdAndProjectId(userId, projectId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "User is not a member of this project"));

        if (userProject.getIsOwner()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Cannot remove project owner");
        }

        userProjectRepository.delete(userProject);
    }

    @Override
    public List<UserProject> getProjectMembers(UUID projectId) {
        if (!projectRepository.existsById(projectId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project with id " + projectId + " not found");
        }

        return userProjectRepository.findByProjectId(projectId);
    }

    @Override
    public List<UserProject> getUserProjects(UUID userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + userId + " not found");
        }

        return userProjectRepository.findByUserId(userId);
    }

    @Override
    public UserProject getProjectOwner(UUID projectId) {
        if (!projectRepository.existsById(projectId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project with id " + projectId + " not found");
        }

        return userProjectRepository
                .findByProjectIdAndIsOwnerTrue(projectId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project owner not found"));
    }

    @Override
    public boolean isUserInProject(UUID userId, UUID projectId) {
        return userProjectRepository.existsByUserIdAndProjectId(userId, projectId);
    }

    @Override
    public boolean isUserOwner(UUID userId, UUID projectId) {
        return userProjectRepository
                .findByUserIdAndProjectIdAndIsOwnerTrue(userId, projectId)
                .isPresent();
    }
}
