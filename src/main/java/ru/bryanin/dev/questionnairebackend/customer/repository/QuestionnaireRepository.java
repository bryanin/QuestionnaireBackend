package ru.bryanin.dev.questionnairebackend.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.bryanin.dev.questionnairebackend.office.model.task.Task;

import java.util.Optional;

@Repository
public interface QuestionnaireRepository extends JpaRepository<Task, Long> {

    @Query("SELECT e.questionnaire FROM Task e WHERE e.id =?1")
    Optional<String> findByTaskId(Long id);
}
