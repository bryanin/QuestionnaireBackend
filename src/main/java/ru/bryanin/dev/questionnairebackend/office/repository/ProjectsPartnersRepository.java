package ru.bryanin.dev.questionnairebackend.office.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.bryanin.dev.questionnairebackend.office.model.project.ProjectsPartners;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectsPartnersRepository extends JpaRepository<ProjectsPartners, Long> {

    @Query("SELECT e FROM ProjectsPartners e WHERE e.projectId =?1")
    Optional<List<ProjectsPartners>> getAllProjectsPartnersByProjectId(Long id);
}
