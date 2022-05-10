package ru.bryanin.dev.questionnairebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bryanin.dev.questionnairebackend.model.task.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
