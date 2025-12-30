package universityproject.taskmanager.userproject.mapper;

import universityproject.taskmanager.userproject.dto.UserProjectResponse;
import universityproject.taskmanager.userproject.model.UserProject;

public class UserProjectMapper {

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
