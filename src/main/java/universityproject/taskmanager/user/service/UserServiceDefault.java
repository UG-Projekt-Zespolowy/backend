package universityproject.taskmanager.user.service;

import static java.util.Objects.nonNull;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import universityproject.taskmanager.user.model.User;
import universityproject.taskmanager.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceDefault implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public User createUser(String name, String keycloakId, String username) {
        if (userRepository.existsByUsername(username)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username " + username + " already exists");
        }
        if (userRepository.existsByKeycloakId(keycloakId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Keycloak ID " + keycloakId + " already exists");
        }

        User user = User.builder()
                .name(name)
                .keycloakId(keycloakId)
                .username(username)
                .build();

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User updateUser(UUID id, String name, String keycloakId, String username) {
        User user = userRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " not found"));

        if (nonNull(name)) {
            user.setName(name);
        }

        if (nonNull(keycloakId)) {
            user.setKeycloakId(keycloakId);
        }

        if (nonNull(username)) {
            if (!user.getUsername().equals(username) && userRepository.existsByUsername(username)) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Username " + username + " already exists");
            }
            user.setUsername(username);
        }

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    public User getUserById(UUID id) {
        return userRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " not found"));
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User with username " + username + " not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
