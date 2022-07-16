package ru.bryanin.dev.questionnairebackend.office.model.task;

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
    @JoinColumn(table = "users", name = "sender_id", referencedColumnName = "id")
    private Long senderId;
    @JoinColumn(table = "tasks", name = "task_id", referencedColumnName = "id")
    private Long taskId;
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;
    @Column(name = "message", nullable = false)
    private String message;

    public Comment() {
    }

    public Comment(Long id, Long senderId, Long taskId, LocalDate createdAt, String message) {
        this.id = id;
        this.senderId = senderId;
        this.taskId = taskId;
        this.createdAt = createdAt;
        this.message = message;
    }
}
