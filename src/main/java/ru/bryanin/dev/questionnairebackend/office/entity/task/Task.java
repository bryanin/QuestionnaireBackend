package ru.bryanin.dev.questionnairebackend.office.entity.task;

import lombok.Data;
import ru.bryanin.dev.questionnairebackend.office.entity.project.ProjectsFiles;

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
    @OneToMany(mappedBy = "comments")
    private List<Comment> commentList;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "securitySystem", nullable = false)
    private SecuritySystem securitySystem;
    @Column(name = "questionnaire", nullable = false, columnDefinition = "TEXT")
    private String questionnaire;
    @Column(name = "performer_email")
    private String performerEmail;
    @ElementCollection(targetClass = Stage.class)
    @CollectionTable(name="tasks_stages")
    @Enumerated(value = EnumType.STRING)
    @Column(name = "stage")
    private Set<Stage> stageSet;
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;
    @Column(name = "guaranteed_due_date")
    private LocalDate guaranteedDueDate;
    @Column(name = "desired_due_date")
    private LocalDate desiredDueDate;
    @Column(name = "actual_launch_date")
    private LocalDate actualLaunchDate;
    @Column(name = "estimated_completion_date")
    private LocalDate estimatedCompletionDate;
    @Column(name = "date_of_actual_completion")
    private LocalDate dateOfActualCompletion;
    @Transient
    @OneToMany(mappedBy = "project_files")
    private List<ProjectsFiles> projectsFiles;

    public Task() {
    }

    public Task(Long id, String ownerEmail, Status status, Complexity complexity, Long projectId, List<Comment> commentList, SecuritySystem securitySystem, String questionnaire, String performerEmail, Set<Stage> stageSet, LocalDate createdAt, LocalDate guaranteedDueDate, LocalDate desiredDueDate, LocalDate actualLaunchDate, LocalDate estimatedCompletionDate, LocalDate dateOfActualCompletion, List<ProjectsFiles> projectsFiles) {
        this.id = id;
        this.ownerEmail = ownerEmail;
        this.status = status;
        this.complexity = complexity;
        this.projectId = projectId;
        this.commentList = commentList;
        this.securitySystem = securitySystem;
        this.questionnaire = questionnaire;
        this.performerEmail = performerEmail;
        this.stageSet = stageSet;
        this.createdAt = createdAt;
        this.guaranteedDueDate = guaranteedDueDate;
        this.desiredDueDate = desiredDueDate;
        this.actualLaunchDate = actualLaunchDate;
        this.estimatedCompletionDate = estimatedCompletionDate;
        this.dateOfActualCompletion = dateOfActualCompletion;
        this.projectsFiles = projectsFiles;
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
        if (securitySystem != task.securitySystem) return false;
        if (!questionnaire.equals(task.questionnaire)) return false;
        if (!Objects.equals(performerEmail, task.performerEmail))
            return false;
        if (!stageSet.equals(task.stageSet)) return false;
        if (!createdAt.equals(task.createdAt)) return false;
        if (!Objects.equals(guaranteedDueDate, task.guaranteedDueDate))
            return false;
        if (!Objects.equals(desiredDueDate, task.desiredDueDate))
            return false;
        if (!Objects.equals(actualLaunchDate, task.actualLaunchDate))
            return false;
        if (!Objects.equals(estimatedCompletionDate, task.estimatedCompletionDate))
            return false;
        if (!Objects.equals(dateOfActualCompletion, task.dateOfActualCompletion))
            return false;
        return Objects.equals(projectsFiles, task.projectsFiles);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + ownerEmail.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + complexity.hashCode();
        result = 31 * result + projectId.hashCode();
        result = 31 * result + (commentList != null ? commentList.hashCode() : 0);
        result = 31 * result + securitySystem.hashCode();
        result = 31 * result + questionnaire.hashCode();
        result = 31 * result + (performerEmail != null ? performerEmail.hashCode() : 0);
        result = 31 * result + stageSet.hashCode();
        result = 31 * result + createdAt.hashCode();
        result = 31 * result + (guaranteedDueDate != null ? guaranteedDueDate.hashCode() : 0);
        result = 31 * result + (desiredDueDate != null ? desiredDueDate.hashCode() : 0);
        result = 31 * result + (actualLaunchDate != null ? actualLaunchDate.hashCode() : 0);
        result = 31 * result + (estimatedCompletionDate != null ? estimatedCompletionDate.hashCode() : 0);
        result = 31 * result + (dateOfActualCompletion != null ? dateOfActualCompletion.hashCode() : 0);
        result = 31 * result + (projectsFiles != null ? projectsFiles.hashCode() : 0);
        return result;
    }
}
