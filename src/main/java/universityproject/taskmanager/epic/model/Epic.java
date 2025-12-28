package universityproject.taskmanager.epic.model;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.*;
import universityproject.taskmanager.project.model.Project;

@Entity
@Table(name = "task_manager_epic")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Epic {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
}
