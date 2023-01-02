package ru.bryanin.dev.questionnairebackend.office.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import ru.bryanin.dev.questionnairebackend.office.model.project.Address;
import ru.bryanin.dev.questionnairebackend.office.model.project.Project;
import ru.bryanin.dev.questionnairebackend.office.model.user.Employee;
import ru.bryanin.dev.questionnairebackend.office.repository.AddressRepository;
import ru.bryanin.dev.questionnairebackend.office.repository.EmployeeRepository;
import ru.bryanin.dev.questionnairebackend.office.repository.ProjectRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;
    private final AddressRepository addressRepository;

    public ProjectService(ProjectRepository projectRepository, EmployeeRepository employeeRepository, AddressRepository addressRepository) {
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
        this.addressRepository = addressRepository;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProject(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new IllegalStateException("Проекта с id = " + id + " не существует"));
    }

    public Project addNewProject(Project newProject) {
        String newProjectTitle = newProject.getTitle();
        Optional<Project> optionalProjectByTitle = projectRepository.findByTitle(newProjectTitle);
        if(optionalProjectByTitle.isPresent()) {
            throw new IllegalStateException("Проект с названием " + newProjectTitle + " уже зарегистрирован");
        }
        String newProject1Cid = newProject.getId_1C();
        Optional<Project> optionalProjectBy1CId = projectRepository.findBy1CId(newProject1Cid);
        if(optionalProjectBy1CId.isPresent()) {
            throw new IllegalStateException("Проект 1C " + newProject1Cid + " уже привязан к проекту " + optionalProjectBy1CId.get().getTitle());
        }
        if(newProject.getCreatedAt() == null) {
            newProject.setCreatedAt(LocalDate.now());
        }
        String newProjectOwnerEmail = newProject.getOwnerEmail();
        Optional<Employee> optionalProjectByEmail = employeeRepository.findBasicUserByEmail(newProjectOwnerEmail);
        if(!optionalProjectByEmail.isPresent()) {
            throw new IllegalStateException("Проект не может быть сохранен, т.к. пользователь с email " + newProjectOwnerEmail + " не зарегистрирован");
        }
        newProject.setStatus(Project.Status.ARCHIVED);
        projectRepository.save(newProject);
        return projectRepository.findByTitle(newProject.getTitle()).get();
    }

    public void deleteProject(Long id) {
        boolean exists = projectRepository.existsById(id);
        if(!exists) {
            throw new IllegalStateException("Проекта с id = " + id + " не существует");
        }
        projectRepository.deleteById(id);
    }

    @Transactional
    public Project updateProject(Long id, Project updatedProject) {

        //    -    this.status = status;
        //    -    this.projectsPartners = projectsPartners;

        Project projectFromDB = projectRepository.findById(id).orElseThrow(() -> new IllegalStateException("Проекта с id = " + id + " не существует"));

        if(updatedProject.getId_1C() != null && updatedProject.getId_1C().length() > 0 && !Objects.equals(updatedProject.getId_1C(), projectFromDB.getId_1C())) {
            Optional<Project> projectOptional = projectRepository.findBy1CId(updatedProject.getId_1C());
            if(projectOptional.isPresent()) {
                throw new IllegalStateException("Проект 1C " + projectOptional.get().getId_1C() + " уже привязан к проекту " + projectOptional.get().getTitle());
            }
            projectFromDB.setId_1C(updatedProject.getId_1C());
        }
        if(updatedProject.getTitle() != null && updatedProject.getTitle().length() > 0 && !Objects.equals(updatedProject.getTitle(), projectFromDB.getTitle())) {
            projectFromDB.setTitle(updatedProject.getTitle());
        }
        if(updatedProject.getDescription() != null && updatedProject.getDescription().length() > 0 && !Objects.equals(updatedProject.getDescription(), projectFromDB.getDescription())) {
            projectFromDB.setDescription(updatedProject.getDescription());
        }
//        if(updatedProject.getOwnerEmail() != null && !Objects.equals(updatedProject.getOwnerEmail(), projectFromDB.getOwnerEmail())) {
//            projectFromDB.setOwnerEmail(updatedProject.getOwnerEmail());
//        }
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
        if(updatedProject.getAddress() != null && !Objects.equals(updatedProject.getAddress(), projectFromDB.getAddress())) {

            Optional<Address> addressOptional = addressRepository.findIfExist(updatedProject.getAddress().getPostalCode(), updatedProject.getAddress().getCountry(), updatedProject.getAddress().getRegion(), updatedProject.getAddress().getCity(), updatedProject.getAddress().getSettlement(), updatedProject.getAddress().getStreet(), updatedProject.getAddress().getHouse(), updatedProject.getAddress().getBlock());
            if(addressOptional.isPresent()) {
                projectFromDB.setAddress(addressOptional.get());
            } else {
                Address newAddress = new Address(updatedProject.getAddress().getPostalCode(), updatedProject.getAddress().getCountry(), updatedProject.getAddress().getRegion(), updatedProject.getAddress().getCity(), updatedProject.getAddress().getSettlement(), updatedProject.getAddress().getStreet(), updatedProject.getAddress().getHouse(), updatedProject.getAddress().getBlock());
                addressRepository.save(newAddress);
                projectFromDB.setAddress(newAddress);
            }

        }
        if(updatedProject.getStatus() != null && !Objects.equals(updatedProject.getStatus(), projectFromDB.getStatus())) {
            projectFromDB.setStatus(updatedProject.getStatus());
        }

        return projectFromDB;

    }
}
