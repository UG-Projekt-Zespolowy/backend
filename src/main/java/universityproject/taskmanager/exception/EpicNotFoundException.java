package universityproject.taskmanager.exception;

import java.util.UUID;

public class EpicNotFoundException extends ResourceNotFoundException {
    public EpicNotFoundException(UUID id) {
        super("Epic with id " + id + " not found");
    }
}
