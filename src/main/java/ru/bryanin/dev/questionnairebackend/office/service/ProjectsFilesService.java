package ru.bryanin.dev.questionnairebackend.office.service;

import org.springframework.stereotype.Service;
import ru.bryanin.dev.questionnairebackend.office.model.project.ProjectsFiles;
import ru.bryanin.dev.questionnairebackend.office.model.task.Task;
import ru.bryanin.dev.questionnairebackend.office.repository.ProjectsFilesRepository;

import java.util.List;

@Service
public class ProjectsFilesService {
    private final ProjectsFilesRepository projectsFilesRepository;

    public ProjectsFilesService(ProjectsFilesRepository projectsFilesRepository) {
        this.projectsFilesRepository = projectsFilesRepository;
    }

    public List<ProjectsFiles> getAllProjectsFilesByProjectId(Long id) {
        return projectsFilesRepository.getAllProjectsFilesByProjectId(id).orElseThrow(() -> new IllegalStateException("Проекта с id = " + id + " не существует"));
    }
}
