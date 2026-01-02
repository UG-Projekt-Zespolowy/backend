package universityproject.taskmanager.user.service;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import universityproject.taskmanager.user.dto.CreateUserRequest;
import universityproject.taskmanager.user.dto.UpdateUserRequest;
import universityproject.taskmanager.user.model.User;

public interface UserService {

    User createUser(CreateUserRequest request);

    User updateUser(UUID id, UpdateUserRequest request);

    void deleteUser(UUID id);

    User getUserById(UUID id);

    User getUserByUsername(String username);

    Page<User> getAllUsers(Pageable pageable);
}
