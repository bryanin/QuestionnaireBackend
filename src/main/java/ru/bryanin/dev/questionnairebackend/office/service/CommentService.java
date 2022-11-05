package ru.bryanin.dev.questionnairebackend.office.service;

import org.springframework.stereotype.Service;
import ru.bryanin.dev.questionnairebackend.office.model.task.Comment;
import ru.bryanin.dev.questionnairebackend.office.model.task.Task;
import ru.bryanin.dev.questionnairebackend.office.repository.TaskCommentRepository;

import java.util.List;

@Service
public class CommentService {

    private final TaskCommentRepository taskCommentRepository;

    public CommentService(TaskCommentRepository taskCommentRepository) {
        this.taskCommentRepository = taskCommentRepository;
    }

    public List<Comment> getAllCommentsByTaskId(Long id) {
        return taskCommentRepository.getAllCommentsByTaskId(id).orElseThrow(() -> new IllegalStateException("Задачи с id = " + id + " не существует"));
    }


}
