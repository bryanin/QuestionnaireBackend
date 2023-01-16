package ru.bryanin.dev.questionnairebackend.office.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.bryanin.dev.questionnairebackend.office.dto.project.ProjectDTO;
import ru.bryanin.dev.questionnairebackend.office.dto.task.TaskDTO;
import ru.bryanin.dev.questionnairebackend.office.entity.project.Address;
import ru.bryanin.dev.questionnairebackend.office.entity.project.Project;
import ru.bryanin.dev.questionnairebackend.office.entity.task.Task;
import ru.bryanin.dev.questionnairebackend.office.entity.user.Employee;
import ru.bryanin.dev.questionnairebackend.office.repository.AddressRepository;
import ru.bryanin.dev.questionnairebackend.office.repository.EmployeeRepository;
import ru.bryanin.dev.questionnairebackend.office.repository.ProjectRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;
    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;
    private final EmployeeService employeeService;
    private final AddressService addressService;
    private final DateTimeFormatter formatter;

    public ProjectService(ProjectRepository projectRepository, EmployeeRepository employeeRepository, AddressRepository addressRepository, ModelMapper modelMapper, EmployeeService employeeService, AddressService addressService) {
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
        this.employeeService = employeeService;
        this.addressService = addressService;
        this.formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    }

    private ProjectDTO convertToDTO(Project project) {
        ProjectDTO projectDTO = modelMapper.map(project, ProjectDTO.class);
        projectDTO.setOwnerLastName(employeeService.getEmployeeByEmail(project.getOwnerEmail()).getLastName());
        projectDTO.setOwnerFirstName(employeeService.getEmployeeByEmail(project.getOwnerEmail()).getFirstName());
        projectDTO.setAddressToString(addressService.getAddressAsStringById(project.getAddress().getId()));
        projectDTO.setCreatedAt(project.getCreatedAt().format(formatter));
        projectDTO.setStatus(project.getStatus().name());
        return projectDTO;
    }

    private Project convertToEntity(ProjectDTO projectDTO) {
        Project project = modelMapper.map(projectDTO, Project.class);
        return project;
    }

    public List<ProjectDTO> getAllProjectsDTO() {
        return projectRepository.findAll().stream().map(project -> convertToDTO(project)).collect(Collectors.toList());
    }

    public ProjectDTO getProjectDTO(Long id) {
        return convertToDTO(projectRepository.findById(id).orElseThrow(() -> new IllegalStateException("Проекта с id = " + id + " не существует")));
    }

    public ProjectDTO addNewProjectDTO(ProjectDTO newProjectDTO) {
        String newProjectTitle = newProjectDTO.getTitle();
        Optional<Project> optionalProjectByTitle = projectRepository.findByTitle(newProjectTitle);
        if(optionalProjectByTitle.isPresent()) {
            throw new IllegalStateException("Проект с названием " + newProjectTitle + " уже зарегистрирован");
        }
        String newProject1Cid = newProjectDTO.getId_1C();
        Optional<Project> optionalProjectBy1CId = projectRepository.findBy1CId(newProject1Cid);
        if(optionalProjectBy1CId.isPresent()) {
            throw new IllegalStateException("Проект 1C " + newProject1Cid + " уже привязан к проекту " + optionalProjectBy1CId.get().getTitle());
        }
        String newProjectOwnerEmail = newProjectDTO.getOwnerEmail();
        Optional<Employee> optionalProjectByEmail = employeeRepository.findBasicUserByEmail(newProjectOwnerEmail);
        if(!optionalProjectByEmail.isPresent()) {
            throw new IllegalStateException("Проект не может быть сохранен, т.к. пользователь с email " + newProjectOwnerEmail + " не зарегистрирован");
        }
        if(newProjectDTO.getCreatedAt() == null) {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            newProjectDTO.setCreatedAt(LocalDate.now().format(formatter));
        }
        newProjectDTO.setOwnerLastName(employeeService.getEmployeeByEmail(newProjectDTO.getOwnerEmail()).getLastName());
        newProjectDTO.setOwnerFirstName(employeeService.getEmployeeByEmail(newProjectDTO.getOwnerEmail()).getFirstName());
        newProjectDTO.setStatus(Project.Status.ARCHIVED.name());
        projectRepository.save(convertToEntity(newProjectDTO));
        return convertToDTO(projectRepository.findByTitle(newProjectDTO.getTitle()).get());
    }

    public void deleteProjectDTO(Long id) {
        boolean exists = projectRepository.existsById(id);
        if(!exists) {
            throw new IllegalStateException("Проекта с id = " + id + " не существует");
        }
        projectRepository.deleteById(id);
    }

    @Transactional
    public ProjectDTO updateProjectDTO(Long id, ProjectDTO updatedProjectDTO) {

        //    -    this.projectsPartners = projectsPartners;

        Project projectFromDB = projectRepository.findById(id).orElseThrow(() -> new IllegalStateException("Проекта с id = " + id + " не существует"));

        if(updatedProjectDTO.getId() == null) {
            throw new IllegalStateException("Проект должен иметь id");
        }

        if(updatedProjectDTO.getId_1C() != null && updatedProjectDTO.getId_1C().length() > 0 && !Objects.equals(updatedProjectDTO.getId_1C(), projectFromDB.getId_1C())) {
            Optional<Project> projectOptional = projectRepository.findBy1CId(updatedProjectDTO.getId_1C());
            if(projectOptional.isPresent()) {
                throw new IllegalStateException("Проект 1C " + projectOptional.get().getId_1C() + " уже привязан к проекту " + projectOptional.get().getTitle());
            }
            projectFromDB.setId_1C(updatedProjectDTO.getId_1C());
        }

        if(updatedProjectDTO.getTitle() != null && updatedProjectDTO.getTitle().length() > 0) {
            if(!Objects.equals(updatedProjectDTO.getTitle(), projectFromDB.getTitle()))
            projectFromDB.setTitle(updatedProjectDTO.getTitle());
        } else {
            throw new IllegalStateException("Проект должен иметь название");
        }

        if(updatedProjectDTO.getDescription() != null && updatedProjectDTO.getDescription().length() > 0 && !Objects.equals(updatedProjectDTO.getDescription(), projectFromDB.getDescription())) {
            projectFromDB.setDescription(updatedProjectDTO.getDescription());
        }

        if(updatedProjectDTO.getOwnerEmail() != null) {
            if(!Objects.equals(updatedProjectDTO.getOwnerEmail(), projectFromDB.getOwnerEmail())) {
                projectFromDB.setOwnerEmail(updatedProjectDTO.getOwnerEmail());
            }
        } else {
            throw new IllegalStateException("В проекте должен быть указан инициатор");
        }

//
//        if(updatedProject.getTaskList() != null) {
//            if(!updatedProject.getTaskList().isEmpty() && updatedProject.getTaskList().size() > 0) {
//                Collections.sort(updatedProject.getTaskList(), new Project.SortList());
//                Collections.sort(projectFromDB.getTaskList(), new Project.SortList());
//                if (!updatedProject.getTaskList().equals(projectFromDB.getTaskList())) {
//                    projectFromDB.setTaskList(updatedProject.getTaskList());
//                }
//            }
//        }
        if(updatedProjectDTO.getAddress() != null && !Objects.equals(updatedProjectDTO.getAddress(), projectFromDB.getAddress())) {
            Optional<Address> addressOptional = addressRepository.findIfExist(
                    updatedProjectDTO.getAddress().getPostalCode(),
                    updatedProjectDTO.getAddress().getCountry(),
                    updatedProjectDTO.getAddress().getRegion(),
                    updatedProjectDTO.getAddress().getCity(),
                    updatedProjectDTO.getAddress().getSettlement(),
                    updatedProjectDTO.getAddress().getStreet(),
                    updatedProjectDTO.getAddress().getHouse(),
                    updatedProjectDTO.getAddress().getBlock());
            if(addressOptional.isPresent()) {
                projectFromDB.setAddress(addressOptional.get());
            } else {
                Address newAddress = new Address(
                        updatedProjectDTO.getAddress().getPostalCode(),
                        updatedProjectDTO.getAddress().getCountry(),
                        updatedProjectDTO.getAddress().getRegion(),
                        updatedProjectDTO.getAddress().getCity(),
                        updatedProjectDTO.getAddress().getSettlement(),
                        updatedProjectDTO.getAddress().getStreet(),
                        updatedProjectDTO.getAddress().getHouse(),
                        updatedProjectDTO.getAddress().getBlock());
                addressRepository.save(newAddress);
                projectFromDB.setAddress(newAddress);
            }

        }

        if(updatedProjectDTO.getStatus() != null) {
            if(!Objects.equals(updatedProjectDTO.getStatus(), projectFromDB.getStatus())) {
                if(Arrays.stream(Project.Status.values()).anyMatch(status -> status.name().equals(updatedProjectDTO.getStatus()))) {
                    projectFromDB.setStatus(Project.Status.valueOf(updatedProjectDTO.getStatus()));
                } else {
                    throw new IllegalStateException("В проекте указан некорректный статус");
                }
            }
        } else {
            throw new IllegalStateException("В проекте должен быть указан статус");
        }

        return convertToDTO(projectFromDB);

    }
}
