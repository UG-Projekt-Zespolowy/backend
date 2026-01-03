package universityproject.taskmanager.keycloak;

import static java.util.Objects.isNull;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import universityproject.taskmanager.keycloak.service.UserSyncService;

@Component
@RequiredArgsConstructor
@Slf4j
public class KeycloakAuthenticationSuccessListener {

    private final UserSyncService userSyncService;

    @EventListener
    public void handleAuthenticationSuccess(AuthenticationSuccessEvent event) {
        log.debug("AuthenticationSuccessEvent received: {}", 
                event.getAuthentication() != null ? event.getAuthentication().getClass().getSimpleName() : "null");

        if (isNull(event.getAuthentication())
                || !(event.getAuthentication() instanceof JwtAuthenticationToken jwtAuth)) {
            log.debug("Event ignored - not a JwtAuthenticationToken");
            return;
        }

        try {
            String keycloakId = jwtAuth.getToken().getSubject();
            log.info("JWT authentication successful, syncing user from Keycloak: keycloakId={}", keycloakId);
            userSyncService.sync(jwtAuth.getToken());
        } catch (Exception e) {
            log.error("Failed to sync user from Keycloak", e);
        }
    }
}

