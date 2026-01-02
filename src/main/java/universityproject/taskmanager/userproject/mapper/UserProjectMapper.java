package universityproject.taskmanager.userproject.mapper;

import lombok.experimental.UtilityClass;
import universityproject.taskmanager.userproject.dto.UserProjectResponse;
import universityproject.taskmanager.userproject.model.UserProject;

@UtilityClass
public final class UserProjectMapper {

    public static UserProjectResponse toResponse(UserProject userProject) {
        return new UserProjectResponse(
                userProject.getId(),
                userProject.getUser().getId(),
                userProject.getProject().getId(),
                userProject.getRole(),
                userProject.getIsOwner(),
                userProject.getJoinedAt());
    }
}
