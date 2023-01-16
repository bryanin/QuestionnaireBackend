package ru.bryanin.dev.questionnairebackend.office.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.bryanin.dev.questionnairebackend.office.entity.task.Comment;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskCommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT e FROM Comment e WHERE e.taskId =?1")
    Optional<List<Comment>> getAllCommentsByTaskId(Long id);
}
