package universityproject.taskmanager.exception;

import java.util.UUID;

public class CannotRemoveProjectOwnerException extends RuntimeException {
    public CannotRemoveProjectOwnerException(UUID userId, UUID projectId) {
        super("Cannot remove user with ID " + userId + " from project with ID " + projectId
                + " because they are the project owner.");
    }
}
