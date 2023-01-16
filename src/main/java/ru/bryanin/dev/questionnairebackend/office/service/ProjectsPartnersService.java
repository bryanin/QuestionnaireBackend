package ru.bryanin.dev.questionnairebackend.office.service;

import org.springframework.stereotype.Service;
import ru.bryanin.dev.questionnairebackend.office.entity.project.ProjectsPartners;
import ru.bryanin.dev.questionnairebackend.office.repository.ProjectsPartnersRepository;

import java.util.List;

@Service
public class ProjectsPartnersService {

    private final ProjectsPartnersRepository projectsPartnersRepository;

    public ProjectsPartnersService(ProjectsPartnersRepository projectsPartnersRepository) {
        this.projectsPartnersRepository = projectsPartnersRepository;
    }

    public List<ProjectsPartners> getAllProjectsPartnersByProjectId(Long id) {
        return projectsPartnersRepository.getAllProjectsPartnersByProjectId(id).orElseThrow(() -> new IllegalStateException("Проекта с id = " + id + " не существует"));
    }
}
