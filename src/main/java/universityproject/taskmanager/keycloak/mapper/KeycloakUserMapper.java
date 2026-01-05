package universityproject.taskmanager.keycloak.mapper;

import lombok.experimental.UtilityClass;
import universityproject.taskmanager.keycloak.KeycloakUserData;
import universityproject.taskmanager.user.model.User;

@UtilityClass
public class KeycloakUserMapper {

    public static User toUser(KeycloakUserData data) {
        return User.builder()
                .keycloakId(data.keycloakId())
                .username(data.username())
                .name(data.name())
                .build();
    }
}
