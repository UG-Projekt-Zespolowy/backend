package universityproject.taskmanager.epic.service;

import java.util.List;
import java.util.UUID;
import universityproject.taskmanager.epic.model.Epic;

public interface EpicService {

    Epic createEpic(String title, String description, UUID projectId);

    Epic updateEpic(UUID epicId, String title, String description);

    void deleteEpic(UUID epicId);

    Epic getEpicById(UUID epicId);

    List<Epic> getProjectEpics(UUID projectId);

    List<Epic> getAllEpics();
}
