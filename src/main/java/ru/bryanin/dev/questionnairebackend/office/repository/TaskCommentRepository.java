package ru.bryanin.dev.questionnairebackend.office.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bryanin.dev.questionnairebackend.office.model.task.Comment;

@Repository
public interface TaskCommentRepository extends JpaRepository<Comment, Long> {
}
