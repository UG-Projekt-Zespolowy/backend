package universityproject.taskmanager.userproject.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import universityproject.taskmanager.exception.*;
import universityproject.taskmanager.project.enums.ProjectRole;
import universityproject.taskmanager.project.model.Project;
import universityproject.taskmanager.project.repository.ProjectRepository;
import universityproject.taskmanager.user.model.User;
import universityproject.taskmanager.user.repository.UserRepository;
import universityproject.taskmanager.userproject.model.UserProject;
import universityproject.taskmanager.userproject.repository.UserProjectRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class UserProjectServiceDefault implements UserProjectService {

    private final UserProjectRepository userProjectRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Override
    public UserProject addUserToProject(UUID userId, UUID projectId, ProjectRole role, Boolean isOwner) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        Project project =
                projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));

        if (userProjectRepository.existsByUserIdAndProjectId(userId, projectId)) {
            throw new UserAlreadyInProjectException(userId, projectId);
        }

        return userProjectRepository.save(UserProject.builder()
                .user(user)
                .project(project)
                .role(role)
                .isOwner(Boolean.TRUE.equals(isOwner))
                .build());
    }

    @Override
    public UserProject updateUserRole(UUID userId, UUID projectId, ProjectRole role) {
        UserProject userProject = userProjectRepository
                .findByUserIdAndProjectId(userId, projectId)
                .orElseThrow(() -> new UserProjectNotFoundException(userId, projectId));

        userProject.setRole(role);
        return userProject;
    }

    @Override
    public void removeUserFromProject(UUID userId, UUID projectId) {
        UserProject userProject = userProjectRepository
                .findByUserIdAndProjectId(userId, projectId)
                .orElseThrow(() -> new UserProjectNotFoundException(userId, projectId));

        if (userProject.getIsOwner()) {
            throw new CannotRemoveProjectOwnerException();
        }

        userProjectRepository.delete(userProject);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserProject> getProjectMembers(UUID projectId, Pageable pageable) {
        if (!projectRepository.existsById(projectId)) {
            throw new ProjectNotFoundException(projectId);
        }
        return userProjectRepository.findByProjectId(projectId, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserProject> getUserProjects(UUID userId, Pageable pageable) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }
        return userProjectRepository.findByUserId(userId, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public UserProject getProjectOwner(UUID projectId) {
        if (!projectRepository.existsById(projectId)) {
            throw new ProjectNotFoundException(projectId);
        }

        return userProjectRepository
                .findByProjectIdAndIsOwnerTrue(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project owner not found"));
    }

    @Override
    public boolean isUserInProject(UUID userId, UUID projectId) {
        return userProjectRepository.existsByUserIdAndProjectId(userId, projectId);
    }

    @Override
    public boolean isUserOwner(UUID userId, UUID projectId) {
        return userProjectRepository
                .findByUserIdAndProjectId(userId, projectId)
                .map(UserProject::getIsOwner)
                .orElse(false);
    }
}
