package ru.bryanin.dev.questionnairebackend.office.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.bryanin.dev.questionnairebackend.office.entity.task.Task;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT e FROM Task e WHERE e.projectId =?1")
    Optional<List<Task>> findAllTasksByProjectId(Long id);

    @Query("SELECT e FROM Task e WHERE e.ownerEmail =?1")
    Optional<List<Task>> getAllTasksByEmployeeAsOwner(String email);

    @Query("SELECT e FROM Task e WHERE e.performerEmail =?1")
    Optional<List<Task>> getAllTasksByEmployeeAsPerformer(String email);

    @Query("SELECT e FROM Task e WHERE e.ownerEmail =?1 or e.performerEmail =?1")
    Optional<List<Task>> getAllTasksByEmployeeAsOwnerAndPerformer(String email);
}
