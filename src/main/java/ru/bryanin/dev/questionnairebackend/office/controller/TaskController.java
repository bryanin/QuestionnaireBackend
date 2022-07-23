package ru.bryanin.dev.questionnairebackend.office.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.bryanin.dev.questionnairebackend.office.model.task.Task;
import ru.bryanin.dev.questionnairebackend.office.service.TaskService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping()
public class TaskController {

    // Generate UUID for every task
    // Generate short password for every task
    // if user wasn't authorised he can type his short password
    //

    @Autowired
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
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
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
    public ResponseEntity<Task> getTask(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getTask(id));
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
    public List<Task> getAllTasksByProjectId(@PathVariable Long id) {
        return taskService.getAllTasksByProjectId(id);
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
    public ResponseEntity<Task> addNewTask(@RequestBody Task task) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.addNewTask(task));
    }

    @DeleteMapping(path = "api/v1/task/{id}")
    @PreAuthorize("hasAnyAuthority(" +
            "'head_of_promotion_department:write', " +
            "'head_of_promotion_department_assistant:write'," +
            "'head_of_design_department:write'," +
            "'head_of_engineer_promotion_department:write'," +
            "'head_of_sales:write')")
    public ResponseEntity<Task> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(path = "api/v1/task/{id}")
    @PreAuthorize("hasAnyAuthority(" +
            "'head_of_promotion_department:write', " +
            "'head_of_promotion_department_assistant:write'," +
            "'head_of_design_department:write'," +
            "'head_of_engineer_promotion_department:write'," +
            "'senior_engineer:read'," +
            "'middle_engineer:read'," +
            "'junior_engineer:read'," +
            "'head_of_sales:write')")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody(required = false) Task task) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.updateTask(id, task));
    }

}
