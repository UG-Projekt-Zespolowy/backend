package universityproject.taskmanager.exception;

import java.util.UUID;

public class UserProjectNotFoundException extends ResourceNotFoundException {
    public UserProjectNotFoundException(UUID userId, UUID projectId) {
        super("User " + userId + " is not a member of project " + projectId);
    }
}
