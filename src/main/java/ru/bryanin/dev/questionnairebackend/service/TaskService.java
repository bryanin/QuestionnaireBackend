package ru.bryanin.dev.questionnairebackend.service;

import org.springframework.stereotype.Service;
import ru.bryanin.dev.questionnairebackend.model.task.Task;
import ru.bryanin.dev.questionnairebackend.repository.TaskRepository;

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

    public Task updateTask(Long id, Task updatedTask) {
        Task taskFromDB = taskRepository.findById(id).orElseThrow(() -> new IllegalStateException("Задачи с id = " + id + " не существует"));

        if(updatedTask.getTaskStatus() != null && !Objects.equals(updatedTask.getTaskStatus(), taskFromDB.getTaskStatus())) {
            taskFromDB.setTaskStatus(updatedTask.getTaskStatus());
        }
        if(updatedTask.getTaskComplexity() != null && !Objects.equals(updatedTask.getTaskComplexity(), taskFromDB.getTaskComplexity())) {
            taskFromDB.setTaskComplexity(updatedTask.getTaskComplexity());
        }
//        if(updatedTask.getSystem() != null && !Objects.equals(updatedTask.getSystem(), taskFromDB.getSystem())) {
//            taskFromDB.setSystem(updatedTask.getSystem());
//        }
        if(updatedTask.getProjectId() != null && !Objects.equals(updatedTask.getProjectId(), taskFromDB.getProjectId())) {
            taskFromDB.setProjectId(updatedTask.getProjectId());
        }
        if(updatedTask.getPerformerId() != null && !Objects.equals(updatedTask.getPerformerId(), taskFromDB.getPerformerId())) {
            taskFromDB.setPerformerId(updatedTask.getPerformerId());
        }
        if(updatedTask.getContent() != null && !Objects.equals(updatedTask.getContent(), taskFromDB.getContent())) {
            taskFromDB.setContent(updatedTask.getContent());
        }

        return taskFromDB;
    }
}
