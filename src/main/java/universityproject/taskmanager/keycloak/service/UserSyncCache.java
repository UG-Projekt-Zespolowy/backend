package universityproject.taskmanager.keycloak.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import universityproject.taskmanager.keycloak.enums.CacheName;

@Component
@Slf4j
public class UserSyncCache {

    @Cacheable(value = CacheName.USER_SYNC_VALUE, key = "#keycloakId")
    public boolean isRecentlySynced(String keycloakId) {
        return false;
    }

    @CachePut(value = CacheName.USER_SYNC_VALUE, key = "#keycloakId")
    public boolean markAsSynced(String keycloakId) {
        return true;
    }

    @CacheEvict(value = CacheName.USER_SYNC_VALUE, key = "#keycloakId")
    public void evict(String keycloakId) {}
}
