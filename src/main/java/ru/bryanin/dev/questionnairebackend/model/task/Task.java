package ru.bryanin.dev.questionnairebackend.model.task;

import lombok.Data;
import ru.bryanin.dev.questionnairebackend.model.project.Project;
import ru.bryanin.dev.questionnairebackend.model.user.BasicUser;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @SequenceGenerator(name = "tasks_sequence", sequenceName = "tasks_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasks_sequence")
    private Long id;
    @Column(name = "task_starter_email", nullable = false)
    private String taskStarterEmail;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "task_status", nullable = false)
    private TaskStatus taskStatus;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "task_complexity", nullable = false)
    private TaskComplexity taskComplexity;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "system", nullable = false)
    private System system;
    @JoinColumn(table = "projects", name = "project_id", referencedColumnName = "id")
    private Long projectId;
    @Transient
    @OneToMany(mappedBy = "comments", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskComment> taskCommentList;
    @JoinColumn(table = "users", name = "performer_id", referencedColumnName = "id")
    private Long performerId;
    @Column(name = "content", columnDefinition="TEXT")
    private String content;

    public Task() {
    }

    public Task(Long id, String taskStarterEmail, TaskStatus taskStatus, TaskComplexity taskComplexity, System system, Long projectId, List<TaskComment> taskCommentList, Long performerId, String content) {
        this.id = id;
        this.taskStarterEmail = taskStarterEmail;
        this.taskStatus = taskStatus;
        this.taskComplexity = taskComplexity;
        this.system = system;
        this.projectId = projectId;
        this.taskCommentList = taskCommentList;
        this.performerId = performerId;
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (!taskStarterEmail.equals(task.taskStarterEmail)) return false;
        if (taskStatus != task.taskStatus) return false;
        if (taskComplexity != task.taskComplexity) return false;
        if (system != task.system) return false;
        if (projectId != null ? !projectId.equals(task.projectId) : task.projectId != null) return false;
        if (taskCommentList != null ? !taskCommentList.equals(task.taskCommentList) : task.taskCommentList != null)
            return false;
        if (performerId != null ? !performerId.equals(task.performerId) : task.performerId != null) return false;
        return content != null ? content.equals(task.content) : task.content == null;
    }

    @Override
    public int hashCode() {
        int result = taskStarterEmail.hashCode();
        result = 31 * result + taskStatus.hashCode();
        result = 31 * result + taskComplexity.hashCode();
        result = 31 * result + system.hashCode();
        result = 31 * result + (projectId != null ? projectId.hashCode() : 0);
        result = 31 * result + (taskCommentList != null ? taskCommentList.hashCode() : 0);
        result = 31 * result + (performerId != null ? performerId.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}
