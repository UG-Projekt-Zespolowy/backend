package universityproject.taskmanager.issue.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import universityproject.taskmanager.issue.enums.IssueStatus;
import universityproject.taskmanager.issue.model.Issue;

@Repository
public interface IssueRepository extends JpaRepository<Issue, UUID> {
    List<Issue> findByEpicId(UUID epicId);

    List<Issue> findByAssigneeId(UUID assigneeId);

    List<Issue> findByReporterId(UUID reporterId);

    List<Issue> findByStatus(IssueStatus status);

    @Query("SELECT i FROM Issue i WHERE i.epic.project.id = :projectId")
    List<Issue> findByProjectId(UUID projectId);
}
