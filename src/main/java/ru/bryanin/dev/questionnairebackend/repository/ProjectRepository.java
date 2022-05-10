package ru.bryanin.dev.questionnairebackend.repository;

import org.checkerframework.checker.nullness.Opt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.bryanin.dev.questionnairebackend.model.project.Project;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("SELECT e FROM Project e WHERE e.title = ?1")
    Optional<Project> findByTitle(String title);

    @Query("SELECT e FROM Project e WHERE e.id_1C = ?1")
    Optional<Project> findBy1CId(String id_1C);
}
