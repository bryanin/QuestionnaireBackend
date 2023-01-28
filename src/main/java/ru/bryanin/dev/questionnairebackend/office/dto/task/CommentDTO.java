package ru.bryanin.dev.questionnairebackend.office.dto.task;

import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private String authorEmail;
    private String authorLastName;
    private String authorFirstName;
    private Long taskId;
    private String createdAt;
    private String message;
    private String filePath;
    private String taskStatus;
    private String taskStatusDescription;
}
