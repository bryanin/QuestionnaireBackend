package ru.bryanin.dev.questionnairebackend.office.service;

import org.springframework.stereotype.Service;
import ru.bryanin.dev.questionnairebackend.office.model.project.Project;
import ru.bryanin.dev.questionnairebackend.office.model.task.Task;
import ru.bryanin.dev.questionnairebackend.office.model.user.Employee;
import ru.bryanin.dev.questionnairebackend.office.repository.EmployeeRepository;
import ru.bryanin.dev.questionnairebackend.office.repository.ProjectRepository;
import ru.bryanin.dev.questionnairebackend.office.repository.TaskRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;

    public TaskService(TaskRepository taskRepository, EmployeeRepository employeeRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTask(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new IllegalStateException("Задачи с id = " + id + " не существует"));
    }

    public List<Task> getAllTasksByProjectId(Long id) {
        return taskRepository.findAllTasksByProjectId(id).orElseThrow(() -> new IllegalStateException("Проекта с id = " + id + " не существует"));
    }

    public Task addNewTask(Task newTask) {
        if(newTask.getCreatedAt() == null) {
            newTask.setCreatedAt(LocalDate.now());
        }
        taskRepository.save(newTask);
        Optional<Task> optionalTaskAfterSaving = taskRepository.findAll().stream().filter(task -> task.equals(newTask)).findFirst();
        // Check up!
        projectRepository.findById(newTask.getProjectId()).get().setStatus(Project.Status.WITH_ACTIVE_TASKS);
        return optionalTaskAfterSaving.orElse(newTask);
    }

    public void deleteTask(Long id) {
        boolean exists = taskRepository.existsById(id);
        if(!exists) {
            throw new IllegalStateException("Задачи с id = " + id + " не существует");
        }
        taskRepository.deleteById(id);
    }

    // ERRORS!
    @Transactional
    public Task updateTask(Long id, Task updatedTask) {
        // Fields:
        // Long id - updating of this field don't provided
        // String ownerEmail - email updating is allowed if email is exist
        // Status status - status updating allowed in right order
        // Complexity complexity
        // Long projectId
        // List<Comment> commentList
        // System system
        // String questionnaire
        // Long performerId
        // LocalDate createdAt
        //

        Task taskFromDB = taskRepository.findById(id).orElseThrow(() -> new IllegalStateException("Задачи с id = " + id + " не существует"));
        if(updatedTask.getOwnerEmail() != null && !Objects.equals(updatedTask.getOwnerEmail(), taskFromDB.getOwnerEmail())) {
            Optional<Employee> userFromDB = employeeRepository.findBasicUserByEmail(updatedTask.getOwnerEmail());
            if(!userFromDB.isPresent()) {
                throw new IllegalStateException("Пользователя с email " + updatedTask.getOwnerEmail() + " не существует");
            }
            taskFromDB.setOwnerEmail(updatedTask.getOwnerEmail());
        }
        if(updatedTask.getStatus() != null && !Objects.equals(updatedTask.getStatus(), taskFromDB.getStatus())) {
            // Right order of changing status!
            taskFromDB.setStatus(updatedTask.getStatus());
        }
        if(updatedTask.getComplexity() != null && !Objects.equals(updatedTask.getComplexity(), taskFromDB.getComplexity())) {
            taskFromDB.setComplexity(updatedTask.getComplexity());
        }
        if(updatedTask.getProjectId() != null && !Objects.equals(updatedTask.getProjectId(), taskFromDB.getProjectId())) {
            taskFromDB.setProjectId(updatedTask.getProjectId());
        }
        if(updatedTask.getQuestionnaire() != null &&  !Objects.equals(updatedTask.getQuestionnaire(), taskFromDB.getQuestionnaire())) {
            taskFromDB.setQuestionnaire(updatedTask.getQuestionnaire());
        }
        if(updatedTask.getPerformerEmail() != null && !Objects.equals(updatedTask.getPerformerEmail(), taskFromDB.getPerformerEmail())) {
            taskFromDB.setPerformerEmail(updatedTask.getPerformerEmail());
        }
//        if(updatedTask.getQuestionnaire() != null && !Objects.equals(updatedTask.getQuestionnaire(), taskFromDB.getQuestionnaire())) {
//            taskFromDB.setQuestionnaire(updatedTask.getQuestionnaire());
//        }

        return taskFromDB;
    }
}
