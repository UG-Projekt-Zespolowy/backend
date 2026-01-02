package universityproject.taskmanager.epic.repository;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import universityproject.taskmanager.epic.model.Epic;

@Repository
public interface EpicRepository extends JpaRepository<Epic, UUID> {
    @Query("SELECT e FROM Epic e JOIN FETCH e.project WHERE e.project.id = :projectId")
    Page<Epic> findByProjectId(UUID projectId, Pageable pageable);
}
