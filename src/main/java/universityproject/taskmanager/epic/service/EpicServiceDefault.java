package universityproject.taskmanager.epic.service;

import static java.util.Objects.nonNull;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import universityproject.taskmanager.epic.model.Epic;
import universityproject.taskmanager.epic.repository.EpicRepository;
import universityproject.taskmanager.project.model.Project;
import universityproject.taskmanager.project.repository.ProjectRepository;

@Service
@RequiredArgsConstructor
public class EpicServiceDefault implements EpicService {

    private final EpicRepository epicRepository;
    private final ProjectRepository projectRepository;

    @Override
    @Transactional
    public Epic createEpic(String title, String description, UUID projectId) {
        Project project = projectRepository
                .findById(projectId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Project with id " + projectId + " not found"));

        Epic epic = Epic.builder()
                .title(title)
                .description(description)
                .project(project)
                .build();

        return epicRepository.save(epic);
    }

    @Override
    @Transactional
    public Epic updateEpic(UUID epicId, String title, String description) {
        Epic epic = epicRepository
                .findById(epicId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Epic with id " + epicId + " not found"));

        if (nonNull(title)) {
            epic.setTitle(title);
        }

        if (nonNull(description)) {
            epic.setDescription(description);
        }

        return epicRepository.save(epic);
    }

    @Override
    @Transactional
    public void deleteEpic(UUID epicId) {
        if (!epicRepository.existsById(epicId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Epic with id " + epicId + " not found");
        }
        epicRepository.deleteById(epicId);
    }

    @Override
    public Epic getEpicById(UUID epicId) {
        return epicRepository
                .findById(epicId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Epic with id " + epicId + " not found"));
    }

    @Override
    public List<Epic> getProjectEpics(UUID projectId) {
        if (!projectRepository.existsById(projectId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project with id " + projectId + " not found");
        }
        return epicRepository.findByProjectId(projectId);
    }

    @Override
    public List<Epic> getAllEpics() {
        return epicRepository.findAll();
    }
}
