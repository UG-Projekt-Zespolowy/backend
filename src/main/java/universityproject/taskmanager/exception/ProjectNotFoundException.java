package universityproject.taskmanager.exception;

import java.util.UUID;

public class ProjectNotFoundException extends ResourceNotFoundException {
    public ProjectNotFoundException(UUID id) {
        super("Project with id " + id + " not found");
    }
}
