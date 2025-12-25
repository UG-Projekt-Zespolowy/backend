package universityproject.taskmanager.epic.model;


import jakarta.persistence.*;
import lombok.*;
import universityproject.taskmanager.project.model.Project;

import java.util.UUID;

@Entity
@Table(name = "EPIC")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
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
