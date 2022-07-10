package ru.bryanin.dev.questionnairebackend.model.task;

import lombok.Data;
import ru.bryanin.dev.questionnairebackend.model.questionnaire.Questionnaire;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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
    private TaskStatus status;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "complexity", nullable = false)
    private TaskComplexity complexity;
    @JoinColumn(table = "projects", name = "project_id", referencedColumnName = "id")
    private Long projectId;
    @Transient
    @OneToMany(mappedBy = "comments", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskComment> commentList;
    @JoinColumn(table = "users", name = "performer_id", referencedColumnName = "id")
    private Long performerId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "questionnaire_id", referencedColumnName = "id")
    private Questionnaire questionnaireId;
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    public Task() {
    }

    public Task(
            Long id,
            String ownerEmail,
            TaskStatus status,
            TaskComplexity complexity,
            Long projectId,
            List<TaskComment> commentList,
            Long performerId,
            //Questionnaire questionnaire,
            LocalDate createdAt
    ) {
        this.id = id;
        this.ownerEmail = ownerEmail;
        this.status = status;
        this.complexity = complexity;
        this.projectId = projectId;
        this.commentList = commentList;
        this.performerId = performerId;
        //this.questionnaire = questionnaire;
        this.createdAt = createdAt;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Task task = (Task) o;
//        if (!ownerEmail.equals(task.ownerEmail)) return false;
//        if (status != task.status) return false;
//        if (complexity != task.complexity) return false;
//        //if (questionnaire != task.questionnaire) return false;
//        if (projectId != null ? !projectId.equals(task.projectId) : task.projectId != null) return false;
//        if (commentList != null ? !commentList.equals(task.commentList) : task.commentList != null) return false;
//        if (performerId != null ? !performerId.equals(task.performerId) : task.performerId != null) return false;
//        return questionnaire != null ? questionnaire.equals(task.questionnaire) : task.questionnaire == null;
//    }

//    @Override
//    public int hashCode() {
//        int result = ownerEmail.hashCode();
//        result = 31 * result + status.hashCode();
//        result = 31 * result + complexity.hashCode();
//        result = 31 * result + (projectId != null ? projectId.hashCode() : 0);
//        result = 31 * result + (commentList != null ? commentList.hashCode() : 0);
//        result = 31 * result + (performerId != null ? performerId.hashCode() : 0);
//        //result = 31 * result + (questionnaire != null ? questionnaire.hashCode() : 0);
//        return result;
//    }
}
