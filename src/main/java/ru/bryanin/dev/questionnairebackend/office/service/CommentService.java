package ru.bryanin.dev.questionnairebackend.office.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import ru.bryanin.dev.questionnairebackend.office.dto.task.CommentDTO;
import ru.bryanin.dev.questionnairebackend.office.dto.task.TaskDTO;
import ru.bryanin.dev.questionnairebackend.office.entity.project.ProjectsFiles;
import ru.bryanin.dev.questionnairebackend.office.entity.task.Comment;
import ru.bryanin.dev.questionnairebackend.office.entity.task.Status;
import ru.bryanin.dev.questionnairebackend.office.entity.user.Employee;
import ru.bryanin.dev.questionnairebackend.office.repository.EmployeeRepository;
import ru.bryanin.dev.questionnairebackend.office.repository.ProjectsFilesRepository;
import ru.bryanin.dev.questionnairebackend.office.repository.TaskCommentRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final TaskCommentRepository taskCommentRepository;
    private final EmployeeRepository employeeRepository;
    private final ProjectsFilesRepository projectsFilesRepository;
    private final ProjectsFilesService projectsFilesService;
    private final TaskService taskService;
    private final ModelMapper modelMapper;
    private final DateTimeFormatter formatter;

    public CommentService(TaskCommentRepository taskCommentRepository, EmployeeRepository employeeRepository, ProjectsFilesRepository projectsFilesRepository, ProjectsFilesService projectsFilesService, TaskService taskService, ModelMapper modelMapper) {
        this.taskCommentRepository = taskCommentRepository;
        this.employeeRepository = employeeRepository;
        this.projectsFilesRepository = projectsFilesRepository;
        this.projectsFilesService = projectsFilesService;
        this.taskService = taskService;
        this.modelMapper = modelMapper;
        this.formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    }

    private CommentDTO convertToDTO(Comment comment) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CommentDTO commentDTO = modelMapper.map(comment, CommentDTO.class);
        Optional<Employee> optionalEmployee = employeeRepository.findBasicUserByEmail(comment.getAuthorEmail());
        if(optionalEmployee.isPresent()) {
            commentDTO.setAuthorLastName(optionalEmployee.get().getLastName());
            commentDTO.setAuthorFirstName(optionalEmployee.get().getFirstName());
        }
        commentDTO.setTaskStatus(comment.getTaskStatus().name());
        commentDTO.setTaskStatusDescription(comment.getTaskStatus().getDescription());
        commentDTO.setCreatedAt(LocalDate.parse(commentDTO.getCreatedAt()).format(formatter));
        return commentDTO;
    }

    private Comment convertToEntity(CommentDTO commentDTO) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Comment comment = modelMapper.map(commentDTO, Comment.class);
        comment.setTaskStatus(Status.valueOf(commentDTO.getTaskStatus()));
        comment.setCreatedAt(LocalDate.parse(commentDTO.getCreatedAt(), formatter));
        return comment;
    }

    public List<CommentDTO> getAllCommentsByTaskIdDTO(Long id) {
        List<Comment> commentList = taskCommentRepository.getAllCommentsByTaskId(id).orElseThrow(() -> new IllegalStateException("Задачи с id = " + id + " не существует"));
        List<CommentDTO> commentListDTO = commentList.stream().map(comment -> convertToDTO(comment)).collect(Collectors.toList());
        return commentListDTO;
    }

    public List<CommentDTO> addNewCommentAndGetAllCommentsByTaskIdDTO(CommentDTO commentDTO) {
        if(commentDTO.getCreatedAt() == null) {
            commentDTO.setCreatedAt(LocalDate.now().format(formatter));
        }
        taskCommentRepository.save(convertToEntity(commentDTO));
        //------------ Если в сообщении есть ссылка на файлы, сохраняем результат в ProjectFiles -----------------//
        Optional<String> optionalCommentDTOfilePath = Optional.ofNullable(commentDTO.getFilePath());
        if(optionalCommentDTOfilePath.isPresent() && !optionalCommentDTOfilePath.get().isEmpty()) {
            TaskDTO taskDTO = taskService.getTaskDTO(commentDTO.getTaskId());
            ProjectsFiles projectsFile = new ProjectsFiles();
            projectsFile.setProjectId(taskDTO.getProjectId());
            projectsFile.setTaskId(taskDTO.getId());
            projectsFile.setFilePath(optionalCommentDTOfilePath.get());
            projectsFilesRepository.save(projectsFile);
        }
        return getAllCommentsByTaskIdDTO(commentDTO.getTaskId());
    }


}
