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
    private final UserSyncCache userSyncCache;

    @Transactional
    public void sync(Jwt jwt) {
        var data = extractor.extract(jwt);
        String keycloakId = data.keycloakId();

        if (userSyncCache.isRecentlySynced(keycloakId)) {
            log.info("User recently synced, skipping: keycloakId={}", keycloakId);
            return;
        }

        log.info("Starting user sync: keycloakId={}, username={}", keycloakId, data.username());

        userRepository
                .findByKeycloakId(keycloakId)
                .ifPresentOrElse(
                        existingUser -> syncExistingUser(existingUser, data), () -> createAndSyncNewUser(data));
    }

    private void syncExistingUser(User existingUser, KeycloakUserData data) {
        log.info("User exists in database: keycloakId={}", data.keycloakId());
        boolean changed = updateUserIfChanged(existingUser, data);
        if (changed) {
            userSyncCache.evict(data.keycloakId());
        } else {
            userSyncCache.markAsSynced(data.keycloakId());
        }
    }

    private void createAndSyncNewUser(KeycloakUserData data) {
        log.info(
                "User not found in database, creating new user: keycloakId={}, username={}",
                data.keycloakId(),
                data.username());
        createNewUser(data);
        userSyncCache.markAsSynced(data.keycloakId());
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
