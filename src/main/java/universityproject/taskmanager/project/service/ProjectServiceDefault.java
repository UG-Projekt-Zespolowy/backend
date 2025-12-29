package universityproject.taskmanager.project.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
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
public class ProjectServiceDefault implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserProjectRepository userProjectRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Project createProject(String name, String description, UUID ownerId) {
        User owner = userRepository
                .findById(ownerId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + ownerId + " not found"));

        Project project = Project.builder().name(name).description(description).build();

        project = projectRepository.save(project);

        UserProject userProject = UserProject.builder()
                .user(owner)
                .project(project)
                .role(ProjectRole.PROJECT_MANAGER)
                .isOwner(true)
                .build();

        userProjectRepository.save(userProject);

        return project;
    }

    @Override
    @Transactional
    public Project updateProject(UUID projectId, String name, String description) {
        Project project = projectRepository
                .findById(projectId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Project with id " + projectId + " not found"));

        if (name != null) {
            project.setName(name);
        }

        if (description != null) {
            project.setDescription(description);
        }

        return projectRepository.save(project);
    }

    @Override
    @Transactional
    public void deleteProject(UUID projectId) {
        if (!projectRepository.existsById(projectId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project with id " + projectId + " not found");
        }

        userProjectRepository.deleteByProjectId(projectId);
        projectRepository.deleteById(projectId);
    }

    @Override
    public Project getProjectById(UUID projectId) {
        return projectRepository
                .findById(projectId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Project with id " + projectId + " not found"));
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public List<Project> getUserProjects(UUID userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + userId + " not found");
        }

        return userProjectRepository.findByUserId(userId).stream()
                .map(UserProject::getProject)
                .collect(Collectors.toList());
    }
}
