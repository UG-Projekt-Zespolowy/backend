package universityproject.taskmanager.userproject.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import universityproject.taskmanager.userproject.model.UserProject;

@Repository
public interface UserProjectRepository extends JpaRepository<UserProject, UUID> {

    @Query("SELECT up FROM UserProject up JOIN FETCH up.user JOIN FETCH up.project WHERE up.project.id = :projectId")
    Page<UserProject> findByProjectId(@Param("projectId") UUID projectId, Pageable pageable);

    @Query("SELECT up FROM UserProject up JOIN FETCH up.user JOIN FETCH up.project WHERE up.user.id = :userId")
    Page<UserProject> findByUserId(@Param("userId") UUID userId, Pageable pageable);

    @Query(
            "SELECT up FROM UserProject up JOIN FETCH up.user JOIN FETCH up.project WHERE up.user.id = :userId AND up.project.id = :projectId")
    Optional<UserProject> findByUserIdAndProjectId(@Param("userId") UUID userId, @Param("projectId") UUID projectId);

    @Query(
            "SELECT up FROM UserProject up JOIN FETCH up.user JOIN FETCH up.project WHERE up.project.id = :projectId AND up.isOwner = true")
    Optional<UserProject> findByProjectIdAndIsOwnerTrue(@Param("projectId") UUID projectId);

    boolean existsByUserIdAndProjectId(UUID userId, UUID projectId);

    void deleteByProjectId(UUID projectId);
}
