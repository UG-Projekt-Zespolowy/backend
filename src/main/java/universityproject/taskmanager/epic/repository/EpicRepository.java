package universityproject.taskmanager.epic.repository;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import universityproject.taskmanager.epic.model.Epic;

@Repository
public interface EpicRepository extends JpaRepository<Epic, UUID> {
    Page<Epic> findByProjectId(UUID projectId, Pageable pageable);
}
