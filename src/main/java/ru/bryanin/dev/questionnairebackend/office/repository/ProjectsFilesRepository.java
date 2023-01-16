package ru.bryanin.dev.questionnairebackend.office.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.bryanin.dev.questionnairebackend.office.entity.project.ProjectsFiles;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectsFilesRepository extends JpaRepository<ProjectsFiles, Long> {

    @Query("SELECT e FROM ProjectsFiles e WHERE e.projectId =?1")
    Optional<List<ProjectsFiles>> getAllProjectsFilesByProjectId(Long id);
}
