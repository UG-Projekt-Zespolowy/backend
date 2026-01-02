package universityproject.taskmanager.user.mapper;

import lombok.experimental.UtilityClass;
import universityproject.taskmanager.user.dto.UserResponse;
import universityproject.taskmanager.user.model.User;

@UtilityClass
public final class UserMapper {
    public static UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getKeycloakId(), user.getName(), user.getUsername());
    }
}
