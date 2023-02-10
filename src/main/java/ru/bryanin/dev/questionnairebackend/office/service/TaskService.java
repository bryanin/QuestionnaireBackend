package ru.bryanin.dev.questionnairebackend.office.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.bryanin.dev.questionnairebackend.office.dto.task.TaskDTO;
import ru.bryanin.dev.questionnairebackend.office.entity.project.Project;
import ru.bryanin.dev.questionnairebackend.office.entity.task.*;
import ru.bryanin.dev.questionnairebackend.office.entity.user.Employee;
import ru.bryanin.dev.questionnairebackend.office.repository.EmployeeRepository;
import ru.bryanin.dev.questionnairebackend.office.repository.ProjectRepository;
import ru.bryanin.dev.questionnairebackend.office.repository.TaskRepository;
import ru.bryanin.dev.questionnairebackend.office.validation.JsonValidator;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;
    private final EmployeeService employeeService;
    private final ProjectService projectService;
    private final DateTimeFormatter formatter;

    public TaskService(TaskRepository taskRepository, EmployeeRepository employeeRepository, ProjectRepository projectRepository, ModelMapper modelMapper, EmployeeService employeeService, ProjectService projectService) {
        this.taskRepository = taskRepository;
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
        this.employeeService = employeeService;
        this.projectService = projectService;
        this.formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    }

    private TaskDTO convertToDTO(Task task) {
        TaskDTO taskDTO = modelMapper.map(task, TaskDTO.class);
        taskDTO.setOwnerLastName(employeeService.getEmployeeByEmail(task.getOwnerEmail()).getLastName());
        taskDTO.setOwnerFirstName(employeeService.getEmployeeByEmail(task.getOwnerEmail()).getFirstName());
        taskDTO.setProjectTitle(projectService.getProjectDTO(task.getProjectId()).getTitle());
        taskDTO.setComplexityDescription(taskDTO.getComplexity().getDescription());
        taskDTO.setSecuritySystemDescription(taskDTO.getSecuritySystemDescription());
        taskDTO.setStatusDescription(taskDTO.getStatus().getDescription());
        taskDTO.setStageSetDescription(taskDTO.getStageSet().stream().map(stage -> stage.getDescription()).collect(Collectors.toSet()));
        Optional<String> optionalPerformerEmail = Optional.ofNullable(task.getPerformerEmail());
        if(optionalPerformerEmail.isPresent()) {
            taskDTO.setPerformerLastName(employeeService.getEmployeeByEmail(task.getPerformerEmail()).getLastName());
            taskDTO.setPerformerFirstName(employeeService.getEmployeeByEmail(task.getPerformerEmail()).getFirstName());
        }
        Optional<LocalDate> optionalCreatedAt = Optional.ofNullable(task.getCreatedAt());
        if(optionalCreatedAt.isPresent()) {
            taskDTO.setCreatedAt(optionalCreatedAt.get().format(formatter));
        }
        Optional<LocalDate> optionalGuaranteedDueDate = Optional.ofNullable(task.getGuaranteedDueDate());
        if(optionalGuaranteedDueDate.isPresent()) {
            taskDTO.setGuaranteedDueDate(optionalGuaranteedDueDate.get().format(formatter));
        }
        Optional<LocalDate> optionalDesiredDueDate = Optional.ofNullable(task.getDesiredDueDate());
        if(optionalDesiredDueDate.isPresent()) {
            taskDTO.setDesiredDueDate(optionalDesiredDueDate.get().format(formatter));
        }
        Optional<LocalDate> optionalActualLaunchDate = Optional.ofNullable(task.getActualLaunchDate());
        if(optionalActualLaunchDate.isPresent()) {
            taskDTO.setActualLaunchDate(optionalActualLaunchDate.get().format(formatter));
        }
        Optional<LocalDate> optionalEstimatedCompletionDate = Optional.ofNullable(task.getEstimatedCompletionDate());
        if(optionalEstimatedCompletionDate.isPresent()) {
            taskDTO.setEstimatedCompletionDate(optionalEstimatedCompletionDate.get().format(formatter));
        }
        Optional<LocalDate> optionalDateOfActualCompletion = Optional.ofNullable(task.getDateOfActualCompletion());
        if(optionalDateOfActualCompletion.isPresent()) {
            taskDTO.setDateOfActualCompletion(optionalDateOfActualCompletion.get().format(formatter));
        }
        return taskDTO;
    }

    private Task convertToEntity(TaskDTO taskDTO) {
        Task task = modelMapper.map(taskDTO, Task.class);
        task.setSecuritySystem(SecuritySystem.valueOf(taskDTO.getSecuritySystem()));
        Optional<String> optionalCreatedAt = Optional.ofNullable(taskDTO.getCreatedAt());
        if(optionalCreatedAt.isPresent()) {
            task.setCreatedAt(LocalDate.parse(taskDTO.getCreatedAt(), formatter));
        } else {
            task.setCreatedAt(LocalDate.now());
        }
        Optional<String> optionalDesiredDueDate = Optional.ofNullable(taskDTO.getDesiredDueDate());
        if(optionalDesiredDueDate.isPresent()) {
            task.setDesiredDueDate(LocalDate.parse(taskDTO.getDesiredDueDate(), formatter));
        }
        return task;
    }

    public List<TaskDTO> getAllTasksDTO() {
        List<TaskDTO> taskDTOList = taskRepository.findAll().stream().map(task -> convertToDTO(task)).collect(Collectors.toList());
        return taskDTOList;
    }

    public TaskDTO getTaskDTO(Long id) {
        return convertToDTO(taskRepository.findById(id).orElseThrow(() -> new IllegalStateException("Задачи с id = " + id + " не существует")));
    }

    public List<TaskDTO> getAllTasksByProjectIdDTO(Long id) {
        List<Task> taskList = taskRepository.findAllTasksByProjectId(id).orElseThrow(() -> new IllegalStateException("Проекта с id = " + id + " не существует"));
        List<TaskDTO> taskListDTO = taskList.stream().map(task -> convertToDTO(task)).collect(Collectors.toList());
        return taskListDTO;
    }

    public List<TaskDTO> getAllTasksByEmployeeIdWithParametersDTO(Long id, boolean owner, boolean performer) {
        Optional<Employee> userFromDB = employeeRepository.findById(id);
        if(!userFromDB.isPresent()) {
            throw new IllegalStateException("Пользователя с id " + id + " не существует");
        }
        String email = userFromDB.get().getEmail();
        List<TaskDTO> taskListDTO = null;
        if(owner && performer) {
            Optional<List<Task>> taskListOptional = taskRepository.getAllTasksByEmployeeAsOwnerAndPerformer(email);
            if(taskListOptional.isPresent()) {
                taskListDTO = taskListOptional.get().stream().map(task -> convertToDTO(task)).collect(Collectors.toList());
            } else {
                throw new IllegalStateException("Пользователь с id " + id + " не связан ни с одной задачей");
            }
        }
        // Добавить функционал, если выбрано owner или performer
        return taskListDTO;
    }

    public TaskDTO addNewTaskDTO(TaskDTO newTaskDTO) {
        //------------ Проверка email инициатора -----------------//
        if(newTaskDTO.getOwnerEmail() != null) {
            Optional<Employee> userFromDB = employeeRepository.findBasicUserByEmail(newTaskDTO.getOwnerEmail());
            if(!userFromDB.isPresent()) {
                throw new IllegalStateException("Пользователя с email " + newTaskDTO.getOwnerEmail() + " не существует");
            }
        } else {
            throw new IllegalStateException("Не указан email инициатора задачи");
        }

        if(newTaskDTO.getCreatedAt() == null) {
            newTaskDTO.setCreatedAt(LocalDate.now().format(formatter));
        }

        if(newTaskDTO.getQuestionnaire() != null) {
            String questionnaire = newTaskDTO.getQuestionnaire();
            boolean questionnaireIsValid = false;
            try {
                questionnaireIsValid = JsonValidator.check(questionnaire);
            } catch (IOException e) {
                System.err.println(e);
            }
            if(!questionnaireIsValid) {
                throw new IllegalStateException("Опросный лист не корректен");
            }
        } else {
            throw new IllegalStateException("Опросный лист не может быть пустым");
        }
        Task newTask = convertToEntity(newTaskDTO);
        taskRepository.save(newTask);
        Optional<Task> optionalTaskAfterSaving = taskRepository.findAll().stream().filter(task -> task.equals(newTask)).findFirst();
        // Check up!
        projectRepository.findById(newTask.getProjectId()).get().setStatus(Project.Status.ACTIVE);
        return convertToDTO(optionalTaskAfterSaving.orElse(newTask));
    }

    public void deleteTaskDTO(Long id) {
        boolean exists = taskRepository.existsById(id);
        if(!exists) {
            throw new IllegalStateException("Задачи с id = " + id + " не существует");
        }
        taskRepository.deleteById(id);
    }

    @Transactional
    public TaskDTO updateTaskDTO(Long id, TaskDTO updatedTaskDTO) {
        Task taskFromDB = taskRepository.findById(id).orElseThrow(() -> new IllegalStateException("Задачи с id = " + id + " не существует"));
        //------------ Проверка email инициатора -----------------//
        if(updatedTaskDTO.getOwnerEmail() != null && !Objects.equals(updatedTaskDTO.getOwnerEmail(), taskFromDB.getOwnerEmail())) {
            Optional<Employee> userFromDB = employeeRepository.findBasicUserByEmail(updatedTaskDTO.getOwnerEmail());
            if(!userFromDB.isPresent()) {
                throw new IllegalStateException("Пользователя с email " + updatedTaskDTO.getOwnerEmail() + " не существует");
            }
            taskFromDB.setOwnerEmail(updatedTaskDTO.getOwnerEmail());
        }
        //------------ Проверка статуса -----------------//
        if(updatedTaskDTO.getStatus() != null) {
            Status[] statuses = Status.values();
            if(!Arrays.stream(statuses).anyMatch(status -> status.equals(updatedTaskDTO.getStatus()))) {
                throw new IllegalStateException("Статус задан неверно");
            }
            if(!Objects.equals(updatedTaskDTO.getStatus(), taskFromDB.getStatus())) {
                taskFromDB.setStatus(updatedTaskDTO.getStatus());
            }
        } else {
            throw new IllegalStateException("Задача должна иметь статус");
        }
        //------------ Проверка сложности -----------------//
        if(updatedTaskDTO.getComplexity() != null ) {
            Complexity[] complexities = Complexity.values();
            if(!Arrays.stream(complexities).anyMatch(complexity -> complexity.equals(updatedTaskDTO.getComplexity()))) {
                throw new IllegalStateException("Сложность задана неверно");
            }
            if(!Objects.equals(updatedTaskDTO.getComplexity(), taskFromDB.getComplexity())) {
                taskFromDB.setComplexity(updatedTaskDTO.getComplexity());
            }
        } else {
            throw new IllegalStateException("Значение сложности должно быть указано");
        }
        //------------ Проверка проекта -----------------//
        if(updatedTaskDTO.getProjectId() != null) {
            Long projectId = updatedTaskDTO.getProjectId();
            Optional<Project> projectFromDB = projectRepository.findById(projectId);
            if(!projectFromDB.isPresent()) {
                throw new IllegalStateException("Проекта с id " + projectId + " не существует");
            }
            if(!Objects.equals(projectId, taskFromDB.getProjectId())) {
                taskFromDB.setProjectId(projectId);
            }
        } else {
            throw new IllegalStateException("Номер проекта должен быть указан");
        }
        //------------ Проверка опросника -----------------//
        if(updatedTaskDTO.getQuestionnaire() != null) {
            String questionnaire = updatedTaskDTO.getQuestionnaire();
            boolean questionnaireIsValid = false;
            try {
                questionnaireIsValid = JsonValidator.check(questionnaire);
            } catch (IOException e) {
                System.err.println(e);
            }
            if(!questionnaireIsValid) {
                throw new IllegalStateException("Опросный лист не корректен");
            }
            if(!Objects.equals(questionnaire, taskFromDB.getQuestionnaire())) {
                taskFromDB.setQuestionnaire(questionnaire);
            }
        } else {
            throw new IllegalStateException("Опросный лист не может быть пустым");
        }
        //------------ Проверка email исполнителя -----------------//
        if(updatedTaskDTO.getPerformerEmail() != null && !Objects.equals(updatedTaskDTO.getPerformerEmail(), taskFromDB.getPerformerEmail())) {
            String email = updatedTaskDTO.getPerformerEmail();
            Optional<Employee> userFromDB = employeeRepository.findBasicUserByEmail(email);
            if(!userFromDB.isPresent()) {
                throw new IllegalStateException("Пользователя с email " + email + " не существует");
            }
            taskFromDB.setPerformerEmail(email);
        }
        //------------ Проверка перечня задач -----------------//
        if(updatedTaskDTO.getStageSet() != null) {
            Set<Stage> stagesSet = updatedTaskDTO.getStageSet();
            if(!Objects.equals(stagesSet, taskFromDB.getStageSet())) {
                taskFromDB.setStageSet(stagesSet);
            }
        } else {
            throw new IllegalStateException("Перечень задач не может быть пустым");
        }
        //------------ Проверка дат -----------------//
        if(updatedTaskDTO.getDesiredDueDate() != null) {
            taskFromDB.setDesiredDueDate(LocalDate.parse(updatedTaskDTO.getDesiredDueDate(), formatter));
        }
        if(updatedTaskDTO.getGuaranteedDueDate() != null) {
            taskFromDB.setGuaranteedDueDate(LocalDate.parse(updatedTaskDTO.getGuaranteedDueDate(), formatter));
        }
        if(updatedTaskDTO.getActualLaunchDate() != null) {
            taskFromDB.setActualLaunchDate(LocalDate.parse(updatedTaskDTO.getActualLaunchDate(), formatter));
        }
        if(updatedTaskDTO.getEstimatedCompletionDate() != null) {
            taskFromDB.setEstimatedCompletionDate(LocalDate.parse(updatedTaskDTO.getEstimatedCompletionDate(), formatter));
        }
        if(updatedTaskDTO.getDateOfActualCompletion() != null) {
            taskFromDB.setDateOfActualCompletion(LocalDate.parse(updatedTaskDTO.getDateOfActualCompletion(), formatter));
        }
        //------------ /// -----------------//
        return convertToDTO(taskFromDB);
    }

        public Map<String, String> getAllAvailableStatuses (Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new IllegalStateException("Задачи с id = " + id + " не существует"));
        Map<String, String> taskStatusesMap = new HashMap<>();
        if(task.getStatus().equals(Status.NEW)) {
            taskStatusesMap.put(Status.TO_BE_COMPLETED_BY_CUSTOMER.name(), Status.TO_BE_COMPLETED_BY_CUSTOMER.getDescription());
            taskStatusesMap.put(Status.UNDER_INITIAL_REVIEW_BY_THE_ENGINEER.name(), Status.UNDER_INITIAL_REVIEW_BY_THE_ENGINEER.getDescription());
        }
        if(task.getStatus().equals(Status.TO_BE_COMPLETED_BY_CUSTOMER)) {
            taskStatusesMap.put(Status.COMPLETED_BY_CUSTOMER.name(), Status.COMPLETED_BY_CUSTOMER.getDescription());
            taskStatusesMap.put(Status.UNDER_INITIAL_REVIEW_BY_THE_ENGINEER.name(), Status.UNDER_INITIAL_REVIEW_BY_THE_ENGINEER.getDescription());
            taskStatusesMap.put(Status.NEW.name(), Status.NEW.getDescription());
        }
        if(task.getStatus().equals(Status.UNDER_INITIAL_REVIEW_BY_THE_ENGINEER)) {
            taskStatusesMap.put(Status.NEW.name(), Status.NEW.getDescription());
            taskStatusesMap.put(Status.UNDER_REVIEW_BY_THE_HEAD_OF_THE_PROJECT_DEPARTMENT.name(), Status.UNDER_REVIEW_BY_THE_HEAD_OF_THE_PROJECT_DEPARTMENT.getDescription());
        }
        if(task.getStatus().equals(Status.UNDER_REVIEW_BY_THE_HEAD_OF_THE_PROJECT_DEPARTMENT)) {
            taskStatusesMap.put(Status.UNDER_INITIAL_REVIEW_BY_THE_ENGINEER.name(), Status.UNDER_INITIAL_REVIEW_BY_THE_ENGINEER.getDescription());
            taskStatusesMap.put(Status.UNDER_REVIEW_BY_THE_ENGINEER.name(), Status.UNDER_REVIEW_BY_THE_ENGINEER.getDescription());
            taskStatusesMap.put(Status.IN_QUEUE.name(), Status.IN_QUEUE.getDescription());
        }
        if(task.getStatus().equals(Status.UNDER_REVIEW_BY_THE_ENGINEER)) {
            taskStatusesMap.put(Status.UNDER_INITIAL_REVIEW_BY_THE_ENGINEER.name(), Status.UNDER_INITIAL_REVIEW_BY_THE_ENGINEER.getDescription());
            taskStatusesMap.put(Status.IN_QUEUE.name(), Status.IN_QUEUE.getDescription());
        }
        return taskStatusesMap;
    }

    public List<Long> quantityOfNewTasks() {
        List<Long> list = new ArrayList<>();
        list.add(Long.valueOf(taskRepository.findAll().size()));
        list.add(taskRepository.findAll().stream().filter(task -> task.getCreatedAt().isAfter(LocalDate.now().minusDays(7))).count());
        return  list;
    }

}
