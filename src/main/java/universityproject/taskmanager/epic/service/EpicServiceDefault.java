package universityproject.taskmanager.epic.service;

import io.micrometer.common.util.StringUtils;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import universityproject.taskmanager.epic.dto.CreateEpicRequest;
import universityproject.taskmanager.epic.dto.UpdateEpicRequest;
import universityproject.taskmanager.epic.model.Epic;
import universityproject.taskmanager.epic.repository.EpicRepository;
import universityproject.taskmanager.exception.EpicNotFoundException;
import universityproject.taskmanager.exception.ProjectNotFoundException;
import universityproject.taskmanager.project.model.Project;
import universityproject.taskmanager.project.repository.ProjectRepository;

@Service
@RequiredArgsConstructor
public class EpicServiceDefault implements EpicService {

    private final EpicRepository epicRepository;
    private final ProjectRepository projectRepository;

    @Override
    @Transactional
    public Epic createEpic(CreateEpicRequest request) {
        Project project = projectRepository
                .findById(request.projectId())
                .orElseThrow(() -> new ProjectNotFoundException(request.projectId()));

        Epic epic = Epic.builder()
                .title(request.title())
                .description(request.description())
                .project(project)
                .build();

        return epicRepository.save(epic);
    }

    @Override
    @Transactional
    public Epic updateEpic(UUID epicId, UpdateEpicRequest request) {
        Epic epic = epicRepository.findById(epicId).orElseThrow(() -> new EpicNotFoundException(epicId));

        if (StringUtils.isNotBlank(request.title())) {
            epic.setTitle(request.title());
        }

        epic.setDescription(request.description());

        return epicRepository.save(epic);
    }

    @Override
    @Transactional
    public void deleteEpic(UUID epicId) {
        Epic epic = epicRepository.findById(epicId).orElseThrow(() -> new EpicNotFoundException(epicId));
        epicRepository.delete(epic);
    }

    @Override
    public Epic getEpicById(UUID epicId) {
        return epicRepository.findById(epicId).orElseThrow(() -> new EpicNotFoundException(epicId));
    }

    @Override
    public Page<Epic> getProjectEpics(UUID projectId, Pageable pageable) {
        if (!projectRepository.existsById(projectId)) {
            throw new ProjectNotFoundException(projectId);
        }
        return epicRepository.findByProjectId(projectId, pageable);
    }

    @Override
    public Page<Epic> getAllEpics(Pageable pageable) {
        return epicRepository.findAll(pageable);
    }
}
