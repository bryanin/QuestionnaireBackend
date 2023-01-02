package ru.bryanin.dev.questionnairebackend.office.service;

import org.springframework.stereotype.Service;
import ru.bryanin.dev.questionnairebackend.office.model.project.Project;
import ru.bryanin.dev.questionnairebackend.office.model.task.Comment;
import ru.bryanin.dev.questionnairebackend.office.model.task.Task;
import ru.bryanin.dev.questionnairebackend.office.repository.TaskCommentRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final TaskCommentRepository taskCommentRepository;

    public CommentService(TaskCommentRepository taskCommentRepository) {
        this.taskCommentRepository = taskCommentRepository;
    }

    public List<Comment> getAllCommentsByTaskId(Long id) {
        return taskCommentRepository.getAllCommentsByTaskId(id).orElseThrow(() -> new IllegalStateException("Задачи с id = " + id + " не существует"));
    }

    public List<Comment> addNewCommentAndGetAllCommentsByTaskId(Comment comment) {
        if(comment.getCreatedAt() == null) {
            comment.setCreatedAt(LocalDate.now());
        }
        taskCommentRepository.save(comment);

        return getAllCommentsByTaskId(comment.getTaskId());
    }


}
