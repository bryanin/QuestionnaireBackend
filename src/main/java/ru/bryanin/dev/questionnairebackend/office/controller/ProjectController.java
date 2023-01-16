package ru.bryanin.dev.questionnairebackend.office.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.bryanin.dev.questionnairebackend.office.dto.project.ProjectDTO;
import ru.bryanin.dev.questionnairebackend.office.entity.project.Project;
import ru.bryanin.dev.questionnairebackend.office.service.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping()
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
    public List<ProjectDTO> getAllProjectsDTO() {
        return projectService.getAllProjectsDTO();
    }

    @GetMapping("/{id}")
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
    public ResponseEntity<ProjectDTO> getProjectDTO(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(projectService.getProjectDTO(id));
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority(" +
            "'head_of_promotion_department:write', " +
            "'head_of_promotion_department_assistant:write'," +
            "'senior_engineer:write'," +
            "'middle_engineer:write'," +
            "'junior_engineer:write'," +
            "'head_of_design_department:write'," +
            "'head_of_engineer_promotion_department:write'," +
            "'head_of_sales:write')")
    public ResponseEntity<ProjectDTO> addNewProjectDTO(@RequestBody ProjectDTO projectDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.addNewProjectDTO(projectDTO));
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAnyAuthority(" +
            "'head_of_promotion_department:write', " +
            "'head_of_promotion_department_assistant:write'," +
            "'head_of_design_department:write'," +
            "'head_of_engineer_promotion_department:write'," +
            "'head_of_sales:write')")
    public ResponseEntity<ProjectDTO> deleteProjectDTO(@PathVariable Long id) {
        projectService.deleteProjectDTO(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping(path = "/{id}")
    @PreAuthorize("hasAnyAuthority(" +
            "'head_of_promotion_department:write', " +
            "'head_of_promotion_department_assistant:write'," +
            "'head_of_design_department:write'," +
            "'head_of_engineer_promotion_department:write'," +
            "'head_of_sales:write')")
    public ResponseEntity<ProjectDTO> updateProjectDTO(@PathVariable Long id, @RequestBody(required = false) ProjectDTO projectDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(projectService.updateProjectDTO(id, projectDTO));
    }
}
