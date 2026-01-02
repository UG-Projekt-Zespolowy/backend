package universityproject.taskmanager.userproject.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import universityproject.taskmanager.userproject.model.UserProject;

@Repository
public interface UserProjectRepository extends JpaRepository<UserProject, UUID> {

    Page<UserProject> findByProjectId(UUID projectId, Pageable pageable);

    Page<UserProject> findByUserId(UUID userId, Pageable pageable);

    Optional<UserProject> findByUserIdAndProjectId(UUID userId, UUID projectId);

    Optional<UserProject> findByProjectIdAndIsOwnerTrue(UUID projectId);

    boolean existsByUserIdAndProjectId(UUID userId, UUID projectId);

    void deleteByProjectId(UUID projectId);
}
