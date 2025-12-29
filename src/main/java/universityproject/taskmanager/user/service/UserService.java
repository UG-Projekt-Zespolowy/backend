package universityproject.taskmanager.user.service;

import java.util.List;
import java.util.UUID;
import universityproject.taskmanager.user.model.User;

public interface UserService {

    User createUser(String name, String keycloakId, String username);

    User updateUser(UUID id, String name, String keycloakId, String username);

    void deleteUser(UUID id);

    User getUserById(UUID id);

    User getUserByUsername(String username);

    List<User> getAllUsers();
}
