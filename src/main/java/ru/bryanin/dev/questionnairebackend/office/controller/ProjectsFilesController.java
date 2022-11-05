package ru.bryanin.dev.questionnairebackend.office.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bryanin.dev.questionnairebackend.office.model.project.ProjectsFiles;
import ru.bryanin.dev.questionnairebackend.office.model.task.Comment;
import ru.bryanin.dev.questionnairebackend.office.service.ProjectsFilesService;

import java.util.List;


@RestController
@RequestMapping("/api/v1/project")
public class ProjectsFilesController {

    private final ProjectsFilesService projectsFilesService;

    public ProjectsFilesController(ProjectsFilesService projectsFilesService) {
        this.projectsFilesService = projectsFilesService;
    }

    @GetMapping("/{id}/files")
    @PreAuthorize("hasAnyAuthority(" +
            "'head_of_promotion_department:read', " +
            "'head_of_promotion_department_assistant:read'," +
            "'head_of_design_department:read'," +
            "'senior_designer:read'," +
            "'middle_designer:read'," +
            "'junior_designer:read'," +
            "'head_of_engineer_promotion_department:read'," +
            "'senior_engineer:read'," +
            "'middle_engineer:read'," +
            "'junior_engineer:read'," +
            "'head_of_sales:read'," +
            "'senior_sales_manager:read'," +
            "'middle_sales_manager:read'," +
            "'junior_sales_manager:read')")
    public List<ProjectsFiles> getAllProjectsFilesByProjectId(@PathVariable Long id) {
        return projectsFilesService.getAllProjectsFilesByProjectId(id);
    }
}
