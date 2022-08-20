package ru.bryanin.dev.questionnairebackend.office.model.task;

import lombok.Data;
import ru.bryanin.dev.questionnairebackend.office.model.project.ProjectsFiles;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @SequenceGenerator(name = "tasks_sequence", sequenceName = "tasks_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasks_sequence")
    private Long id;
    @Column(name = "owner_email", nullable = false)
    private String ownerEmail;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "complexity", nullable = false)
    private Complexity complexity;
    @JoinColumn(table = "projects", name = "project_id", referencedColumnName = "id")
    private Long projectId;
    @Transient
    @OneToMany(mappedBy = "comments", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "system", nullable = false)
    private System system;
    @Column(name = "questionnaire", nullable = false, columnDefinition = "TEXT")
    private String questionnaire;
    @Column(name = "performer_email")
    private String performerEmail;
    @ElementCollection(targetClass = Stage.class)
    @CollectionTable(name="tasks_stages")
    @Enumerated(value = EnumType.STRING)
    @Column(name = "stage")
    private Set<Stage> stageList;
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;
    @Transient
    @OneToMany(mappedBy = "project_files")
    private List<ProjectsFiles> projectsFiles;

    public Task() {
    }

    public Task(Long id, String ownerEmail, Status status, Complexity complexity, Long projectId, List<Comment> commentList, System system, String questionnaire, String performerEmail, Set<Stage> stageList, LocalDate createdAt) {
        this.id = id;
        this.ownerEmail = ownerEmail;
        this.status = status;
        this.complexity = complexity;
        this.projectId = projectId;
        this.commentList = commentList;
        this.system = system;
        this.questionnaire = questionnaire;
        this.performerEmail = performerEmail;
        this.stageList = stageList;
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (!id.equals(task.id)) return false;
        if (!ownerEmail.equals(task.ownerEmail)) return false;
        if (status != task.status) return false;
        if (complexity != task.complexity) return false;
        if (!projectId.equals(task.projectId)) return false;
        if (!Objects.equals(commentList, task.commentList)) return false;
        if (system != task.system) return false;
        if (!questionnaire.equals(task.questionnaire)) return false;
        if (!Objects.equals(performerEmail, task.performerEmail))
            return false;
        if (!Objects.equals(stageList, task.stageList)) return false;
        return createdAt.equals(task.createdAt);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + ownerEmail.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + complexity.hashCode();
        result = 31 * result + projectId.hashCode();
        result = 31 * result + (commentList != null ? commentList.hashCode() : 0);
        result = 31 * result + system.hashCode();
        result = 31 * result + questionnaire.hashCode();
        result = 31 * result + (performerEmail != null ? performerEmail.hashCode() : 0);
        result = 31 * result + (stageList != null ? stageList.hashCode() : 0);
        result = 31 * result + createdAt.hashCode();
        return result;
    }
}
