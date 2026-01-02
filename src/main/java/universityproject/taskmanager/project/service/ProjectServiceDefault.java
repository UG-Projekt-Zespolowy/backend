package universityproject.taskmanager.project.service;

import io.micrometer.common.util.StringUtils;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import universityproject.taskmanager.exception.ProjectNotFoundException;
import universityproject.taskmanager.exception.UserNotFoundException;
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
public class ProjectServiceDefault implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserProjectRepository userProjectRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Project createProject(String name, String description, UUID ownerId) {
        User owner = userRepository.findById(ownerId).orElseThrow(() -> new UserNotFoundException(ownerId));

        Project project = Project.builder().name(name).description(description).build();

        projectRepository.save(project);

        userProjectRepository.save(UserProject.builder()
                .user(owner)
                .project(project)
                .role(ProjectRole.PROJECT_MANAGER)
                .isOwner(true)
                .build());

        return project;
    }

    @Override
    @Transactional
    public Project updateProject(UUID projectId, String name, String description) {
        Project project =
                projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));

        if (StringUtils.isNotBlank(name)) {
            project.setName(name);
        }
        if (StringUtils.isNotBlank(description)) {
            project.setDescription(description);
        }

        return projectRepository.save(project);
    }

    @Override
    @Transactional
    public void deleteProject(UUID projectId) {
        if (!projectRepository.existsById(projectId)) {
            throw new ProjectNotFoundException(projectId);
        }

        userProjectRepository.deleteByProjectId(projectId);
        projectRepository.deleteById(projectId);
    }

    @Override
    @Transactional(readOnly = true)
    public Project getProjectById(UUID projectId) {
        return projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Project> getAllProjects(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Project> getUserProjects(UUID userId, Pageable pageable) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }

        return userProjectRepository.findByUserId(userId, pageable).map(UserProject::getProject);
    }
}
