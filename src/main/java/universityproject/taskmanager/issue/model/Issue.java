package universityproject.taskmanager.issue.model;


import jakarta.persistence.*;
import lombok.*;
import universityproject.taskmanager.epic.model.Epic;
import universityproject.taskmanager.issue.enums.IssueStatus;
import universityproject.taskmanager.user.model.User;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ISSUE")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "story_point")
    private Integer storyPoint;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IssueStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporter_id", nullable = false)
    private User reporter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id")
    private User assignee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "epic_id")
    private Epic epic;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (status == null) {
            status = IssueStatus.TO_DO;
        }
    }
}
