package universityproject.taskmanager.exception;

import java.util.UUID;

public class IssueNotFoundException extends ResourceNotFoundException {
    public IssueNotFoundException(UUID id) {
        super("Issue with id " + id + " not found");
    }
}
