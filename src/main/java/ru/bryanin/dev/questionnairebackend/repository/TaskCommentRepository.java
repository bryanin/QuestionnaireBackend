package ru.bryanin.dev.questionnairebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bryanin.dev.questionnairebackend.model.task.TaskComment;

@Repository
public interface TaskCommentRepository extends JpaRepository<TaskComment, Long> {
}
