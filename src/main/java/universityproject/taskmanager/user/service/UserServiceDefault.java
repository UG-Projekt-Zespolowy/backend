package universityproject.taskmanager.user.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import universityproject.taskmanager.exception.UserConflictException;
import universityproject.taskmanager.exception.UserNotFoundException;
import universityproject.taskmanager.user.dto.CreateUserRequest;
import universityproject.taskmanager.user.dto.UpdateUserRequest;
import universityproject.taskmanager.user.model.User;
import universityproject.taskmanager.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceDefault implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public User createUser(CreateUserRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new UserConflictException("Username " + request.username() + " already exists");
        }
        if (userRepository.existsByKeycloakId(request.keycloakId())) {
            throw new UserConflictException("Keycloak ID " + request.keycloakId() + " already exists");
        }

        User user = User.builder()
                .name(request.name())
                .keycloakId(request.keycloakId())
                .username(request.username())
                .build();

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User updateUser(UUID id, UpdateUserRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        if (request.name() != null && !request.name().isBlank()) {
            user.setName(request.name());
        }

        if (request.keycloakId() != null && !request.keycloakId().isBlank()) {
            user.setKeycloakId(request.keycloakId());
        }

        if (request.username() != null && !request.username().isBlank()) {
            if (!user.getUsername().equals(request.username()) && userRepository.existsByUsername(request.username())) {
                throw new UserConflictException("Username " + request.username() + " already exists");
            }
            user.setUsername(request.username());
        }

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(user);
    }

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
