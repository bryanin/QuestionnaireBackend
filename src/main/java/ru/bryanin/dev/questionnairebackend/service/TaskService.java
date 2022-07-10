package ru.bryanin.dev.questionnairebackend.service;

import org.springframework.stereotype.Service;
import ru.bryanin.dev.questionnairebackend.model.task.Task;
import ru.bryanin.dev.questionnairebackend.repository.TaskRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTask(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new IllegalStateException("Задачи с id = " + id + " не существует"));
    }

    public Task addNewTask(Task newTask) {
        Optional<Task> optionalTaskBeforeSaving = taskRepository.findAll().stream().filter(task -> task.equals(newTask)).findFirst();
        if(optionalTaskBeforeSaving.isPresent()) {
            throw new IllegalStateException("Задача уже существует");
        }
        taskRepository.save(newTask);
        Optional<Task> optionalTaskAfterSaving = taskRepository.findAll().stream().filter(task -> task.equals(newTask)).findFirst();
        return optionalTaskAfterSaving.orElse(newTask);
    }

    public void deleteTask(Long id) {
        boolean exists = taskRepository.existsById(id);
        if(!exists) {
            throw new IllegalStateException("Задачи с id = " + id + " не существует");
        }
    }

    @Transactional
    public Task updateTask(Long id, Task updatedTask) {
        Task taskFromDB = taskRepository.findById(id).orElseThrow(() -> new IllegalStateException("Задачи с id = " + id + " не существует"));

        if(updatedTask.getStatus() != null && !Objects.equals(updatedTask.getStatus(), taskFromDB.getStatus())) {
            taskFromDB.setStatus(updatedTask.getStatus());
        }
        if(updatedTask.getComplexity() != null && !Objects.equals(updatedTask.getComplexity(), taskFromDB.getComplexity())) {
            taskFromDB.setComplexity(updatedTask.getComplexity());
        }
        if(updatedTask.getProjectId() != null && !Objects.equals(updatedTask.getProjectId(), taskFromDB.getProjectId())) {
            taskFromDB.setProjectId(updatedTask.getProjectId());
        }
        if(updatedTask.getPerformerId() != null && !Objects.equals(updatedTask.getPerformerId(), taskFromDB.getPerformerId())) {
            taskFromDB.setPerformerId(updatedTask.getPerformerId());
        }
//        if(updatedTask.getQuestionnaire() != null && !Objects.equals(updatedTask.getQuestionnaire(), taskFromDB.getQuestionnaire())) {
//            taskFromDB.setQuestionnaire(updatedTask.getQuestionnaire());
//        }

        return taskFromDB;
    }
}
