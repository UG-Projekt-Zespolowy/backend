package universityproject.taskmanager.project.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import universityproject.taskmanager.project.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID> {}
