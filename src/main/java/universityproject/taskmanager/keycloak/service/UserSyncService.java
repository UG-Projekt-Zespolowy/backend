package universityproject.taskmanager.keycloak.service;

import static java.util.Objects.nonNull;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import universityproject.taskmanager.keycloak.KeycloakUserData;
import universityproject.taskmanager.keycloak.KeycloakUserExtractor;
import universityproject.taskmanager.keycloak.mapper.KeycloakUserMapper;
import universityproject.taskmanager.user.model.User;
import universityproject.taskmanager.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserSyncService {

    private final UserRepository userRepository;
    private final KeycloakUserExtractor extractor;

    @Transactional
    public void sync(Jwt jwt) {
        try {
            var data = extractor.extract(jwt);
            String keycloakId = data.keycloakId();

            log.info("Starting user sync: keycloakId={}, username={}", keycloakId, data.username());

            userRepository
                    .findByKeycloakId(keycloakId)
                    .ifPresentOrElse(
                            existingUser -> {
                                log.debug("User exists in database: keycloakId={}", keycloakId);
                                updateUserIfChanged(existingUser, data);
                            },
                            () -> {
                                log.info(
                                        "User not found in database, creating new user: keycloakId={}, username={}",
                                        keycloakId,
                                        data.username());
                                createNewUser(data);
                            });
        } catch (Exception e) {
            log.error("Error during user sync", e);
            throw e;
        }
    }

    private boolean updateUserIfChanged(User user, KeycloakUserData data) {
        boolean updated = false;

        if (nonNull(data.username()) && !data.username().equals(user.getUsername())) {
            user.setUsername(data.username());
            updated = true;
        }

        if (nonNull(data.name()) && !data.name().equals(user.getName())) {
            user.setName(data.name());
            updated = true;
        }

        if (updated) {
            userRepository.save(user);
            log.info("User updated from Keycloak: keycloakId={}, username={}", data.keycloakId(), data.username());
        }

        return updated;
    }

    private void createNewUser(KeycloakUserData data) {
        try {
            User user = KeycloakUserMapper.toUser(data);
            userRepository.save(user);
            log.info("User created from Keycloak: keycloakId={}, username={}", data.keycloakId(), data.username());
        } catch (DataIntegrityViolationException e) {
            log.warn("User already exists for keycloakId={}, username={}", data.keycloakId(), data.username());
        } catch (Exception e) {
            log.error(
                    "Failed to create user from Keycloak: keycloakId={}, username={}",
                    data.keycloakId(),
                    data.username(),
                    e);
            throw e;
        }
    }
}
