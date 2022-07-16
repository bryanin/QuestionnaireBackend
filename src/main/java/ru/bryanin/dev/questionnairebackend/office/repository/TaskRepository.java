package ru.bryanin.dev.questionnairebackend.office.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.bryanin.dev.questionnairebackend.office.model.task.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT e FROM Task e WHERE e.projectId =?1")
    Optional<List<Task>> findAllTasksByProjectId(Long id);
}
