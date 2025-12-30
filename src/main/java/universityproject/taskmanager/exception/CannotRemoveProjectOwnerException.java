package universityproject.taskmanager.exception;

public class CannotRemoveProjectOwnerException extends RuntimeException {
    public CannotRemoveProjectOwnerException() {
        super("Cannot remove project owner");
    }
}
