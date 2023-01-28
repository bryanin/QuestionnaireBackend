package ru.bryanin.dev.questionnairebackend.office.entity.task;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @SequenceGenerator(name = "comments_sequence", sequenceName = "comments_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comments_sequence")
    private Long id;
    @JoinColumn(table = "users", name = "author_email", referencedColumnName = "email", nullable = false)
    private String authorEmail;
    @JoinColumn(table = "tasks", name = "task_id", referencedColumnName = "id", nullable = false)
    private Long taskId;
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;
    @Column(name = "message", nullable = false)
    private String message;
    @Column(name = "file_path")
    private String filePath;
    @JoinColumn(table = "tasks", name = "task_status", referencedColumnName = "status", nullable = false)
    private Status taskStatus;

    public Comment() {
    }

    public Comment(Long id, String authorEmail, Long taskId, LocalDate createdAt, String message, String filePath, Status taskStatus) {
        this.id = id;
        this.authorEmail = authorEmail;
        this.taskId = taskId;
        this.createdAt = createdAt;
        this.message = message;
        this.filePath = filePath;
        this.taskStatus = taskStatus;
    }
}
