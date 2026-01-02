package universityproject.taskmanager.issue.repository;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import universityproject.taskmanager.issue.model.Issue;

@Repository
public interface IssueRepository extends JpaRepository<Issue, UUID> {
    Page<Issue> findByEpicId(UUID epicId, Pageable pageable);

    Page<Issue> findByAssigneeId(UUID assigneeId, Pageable pageable);

    @Query("SELECT i FROM Issue i JOIN i.epic e JOIN e.project WHERE e.project.id = :projectId")
    Page<Issue> findByProjectId(UUID projectId, Pageable pageable);
}
