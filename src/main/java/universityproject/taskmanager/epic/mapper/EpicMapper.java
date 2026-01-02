package universityproject.taskmanager.epic.mapper;

import lombok.experimental.UtilityClass;
import universityproject.taskmanager.epic.dto.EpicResponse;
import universityproject.taskmanager.epic.model.Epic;

@UtilityClass
public final class EpicMapper {
    public static EpicResponse toResponse(Epic epic) {
        return new EpicResponse(
                epic.getId(),
                epic.getTitle(),
                epic.getDescription(),
                epic.getProject().getId());
    }
}
