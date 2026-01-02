package universityproject.taskmanager.exception;

import java.util.UUID;

public class UserAlreadyInProjectException extends RuntimeException {
    public UserAlreadyInProjectException(UUID userId, UUID projectId) {
        super("User " + userId + " is already a member of project " + projectId);
    }
}
