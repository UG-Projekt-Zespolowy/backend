package universityproject.taskmanager.userproject.model;

import static java.util.Objects.isNull;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.*;
import universityproject.taskmanager.project.enums.ProjectRole;
import universityproject.taskmanager.project.model.Project;
import universityproject.taskmanager.user.model.User;

@Entity
@Table(
        name = "task_manager_user_project",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "project_id"})})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class UserProject {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectRole role;

    @Column(name = "is_owner", nullable = false)
    private Boolean isOwner;

    @Column(name = "joined_at", nullable = false)
    private LocalDateTime joinedAt;

    @PrePersist
    protected void onCreate() {
        joinedAt = LocalDateTime.now();
        if (isNull(isOwner)) {
            isOwner = false;
        }
    }
}
