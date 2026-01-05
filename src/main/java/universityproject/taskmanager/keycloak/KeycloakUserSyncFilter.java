package universityproject.taskmanager.keycloak;

import static java.util.Objects.nonNull;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import universityproject.taskmanager.keycloak.service.UserSyncService;

@Component
@RequiredArgsConstructor
@Slf4j
public class KeycloakUserSyncFilter extends OncePerRequestFilter {

    private final UserSyncService userSyncService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        log.debug("Filter called for path: {}", request.getRequestURI());

        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.debug(
                "Authentication type: {}, authenticated: {}",
                nonNull(authentication) ? authentication.getClass().getSimpleName() : "null",
                nonNull(authentication) && authentication.isAuthenticated());

        if (authentication instanceof JwtAuthenticationToken jwtAuth) {
            try {
                String keycloakId = jwtAuth.getToken().getSubject();
                log.debug(
                        "JWT token found, syncing user from Keycloak: keycloakId={}, path={}",
                        keycloakId,
                        request.getRequestURI());
                userSyncService.sync(jwtAuth.getToken());
            } catch (Exception e) {
                log.error("Failed to sync user from Keycloak", e);
            }
        } else {
            log.debug(
                    "No JWT token found - authentication is not JwtAuthenticationToken. Type: {}",
                    nonNull(authentication) ? authentication.getClass().getName() : "null");
        }

        filterChain.doFilter(request, response);
    }
}
