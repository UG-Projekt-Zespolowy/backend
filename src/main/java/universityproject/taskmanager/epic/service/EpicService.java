package universityproject.taskmanager.epic.service;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import universityproject.taskmanager.epic.dto.CreateEpicRequest;
import universityproject.taskmanager.epic.dto.UpdateEpicRequest;
import universityproject.taskmanager.epic.model.Epic;

public interface EpicService {
    Epic createEpic(CreateEpicRequest request);

    Epic updateEpic(UUID epicId, UpdateEpicRequest request);

    void deleteEpic(UUID epicId);

    Epic getEpicById(UUID epicId);

    Page<Epic> getProjectEpics(UUID projectId, Pageable pageable);

    Page<Epic> getAllEpics(Pageable pageable);
}
