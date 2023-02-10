package ru.bryanin.dev.questionnairebackend.office.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.bryanin.dev.questionnairebackend.office.dto.task.TaskDTO;
import ru.bryanin.dev.questionnairebackend.office.service.TaskService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping()
public class TaskController {

    // TO DO
    // Generate UUID for every task
    // Generate short password for every task
    // if user wasn't authorised he can type his short password
    //

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/api/v1/task")
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
    public List<TaskDTO> getAllTasksDTO() {
        return taskService.getAllTasksDTO();
    }

    @GetMapping("/api/v1/task/{id}")
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
    public ResponseEntity<TaskDTO> getTaskDTO(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getTaskDTO(id));
    }

    @GetMapping("/api/v1/project/{id}/task")
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
    public List<TaskDTO> getAllTasksByProjectIdDTO(@PathVariable Long id) {
        return taskService.getAllTasksByProjectIdDTO(id);
    }

    @GetMapping("/api/v1/employee/{id}/task")
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
    public List<TaskDTO> getAllTasksByEmployeeIdWithParametersDTO(@PathVariable Long id,
                                                                  @RequestParam(required = false) boolean owner,
                                                                  @RequestParam(required = false) boolean performer
                                                                  ) {
        return taskService.getAllTasksByEmployeeIdWithParametersDTO(id, owner, performer);
    }

    @PostMapping("/api/v1/task")
    @PreAuthorize("hasAnyAuthority(" +
            "'head_of_promotion_department:write', " +
            "'head_of_promotion_department_assistant:write'," +
            "'head_of_design_department:write'," +
            "'head_of_engineer_promotion_department:write'," +
            "'senior_engineer:read'," +
            "'middle_engineer:read'," +
            "'junior_engineer:read'," +
            "'head_of_sales:write')")
    public ResponseEntity<TaskDTO> addNewTaskDTO(@RequestBody TaskDTO taskDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.addNewTaskDTO(taskDTO));
    }

    @DeleteMapping(path = "api/v1/task/{id}")
    @PreAuthorize("hasAnyAuthority(" +
            "'head_of_promotion_department:write', " +
            "'head_of_promotion_department_assistant:write'," +
            "'head_of_design_department:write'," +
            "'head_of_engineer_promotion_department:write'," +
            "'head_of_sales:write')")
    public ResponseEntity<TaskDTO> deleteTaskDTO(@PathVariable Long id) {
        taskService.deleteTaskDTO(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping(path = "api/v1/task/{id}")
    @PreAuthorize("hasAnyAuthority(" +
            "'head_of_promotion_department:write', " +
            "'head_of_promotion_department_assistant:write'," +
            "'head_of_design_department:write'," +
            "'head_of_engineer_promotion_department:write'," +
            "'senior_engineer:read'," +
            "'middle_engineer:read'," +
            "'junior_engineer:read'," +
            "'head_of_sales:write')")
    public ResponseEntity<TaskDTO> updateTaskDTO(@PathVariable Long id, @RequestBody(required = false) TaskDTO taskDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.updateTaskDTO(id, taskDTO));
    }

    @GetMapping("/api/v1/task/{id}/status")
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
    public Map<String, String> getAllAvailableStatuses(@PathVariable Long id) {
        return taskService.getAllAvailableStatuses(id);
    }

    @GetMapping("/api/v1/task/quantity")
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
    public List<Long> quantityOfNewTasks() {
        return taskService.quantityOfNewTasks();
    }

}
