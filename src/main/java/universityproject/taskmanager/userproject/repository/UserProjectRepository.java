package universityproject.taskmanager.userproject.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import universityproject.taskmanager.project.enums.ProjectRole;
import universityproject.taskmanager.userproject.model.UserProject;

@Repository
public interface UserProjectRepository extends JpaRepository<UserProject, UUID> {

    List<UserProject> findByUserId(UUID userId);

    List<UserProject> findByProjectId(UUID projectId);

    Optional<UserProject> findByUserIdAndProjectId(UUID userId, UUID projectId);

    Optional<UserProject> findByUserIdAndProjectIdAndIsOwnerTrue(UUID userId, UUID projectId);

    Optional<UserProject> findByProjectIdAndIsOwnerTrue(UUID projectId);

    List<UserProject> findByProjectIdAndRole(UUID projectId, ProjectRole role);

    boolean existsByUserIdAndProjectId(UUID userId, UUID projectId);

    void deleteByProjectId(UUID projectId);
}
