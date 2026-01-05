package universityproject.taskmanager.config;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CorsProperties {

    public static final String FRONTEND_URL = "http://localhost:3000";
    public static final String ALL_HEADERS = "*";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String ALL_PATHS = "/**";
}
