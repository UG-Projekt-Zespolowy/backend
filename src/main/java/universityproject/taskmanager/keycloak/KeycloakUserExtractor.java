package universityproject.taskmanager.keycloak;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Slf4j
public class KeycloakUserExtractor {

    public KeycloakUserData extract(Jwt jwt) {
        String keycloakId = jwt.getSubject();
        if (!StringUtils.hasText(keycloakId)) {
            log.error("JWT extraction failed: missing subject (sub) claim");
            throw new IllegalStateException("JWT without sub");
        }

        String username = jwt.getClaimAsString(KeycloakJwtClaims.PREFERRED_USERNAME);
        if (!StringUtils.hasText(username)) {
            log.debug("JWT missing preferred_username, falling back to email");
            username = jwt.getClaimAsString(KeycloakJwtClaims.EMAIL);
        }
        if (!StringUtils.hasText(username)) {
            log.error(
                    "JWT extraction failed: missing both preferred_username and email claims, keycloakId={}",
                    keycloakId);
            throw new IllegalStateException("JWT without username or email");
        }

        String name = jwt.getClaimAsString(KeycloakJwtClaims.NAME);
        if (!StringUtils.hasText(name)) {
            log.debug("JWT missing name claim, using username as fallback, keycloakId={}", keycloakId);
            name = username;
        }

        log.debug("Successfully extracted user data from JWT: keycloakId={}, username={}", keycloakId, username);
        return new KeycloakUserData(keycloakId, username, name);
    }
}
