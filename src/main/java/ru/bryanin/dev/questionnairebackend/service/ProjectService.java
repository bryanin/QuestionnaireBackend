package ru.bryanin.dev.questionnairebackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bryanin.dev.questionnairebackend.model.project.Project;
import ru.bryanin.dev.questionnairebackend.model.user.BasicUser;
import ru.bryanin.dev.questionnairebackend.repository.ProjectRepository;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
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
        if(updatedProject.getOwnerId() != null && !Objects.equals(updatedProject.getOwnerId(), projectFromDB.getOwnerId())) {
            projectFromDB.setOwnerId(updatedProject.getOwnerId());
        }
        if(!updatedProject.getTaskList().isEmpty() && updatedProject.getTaskList().size() > 0) {
            Collections.sort(updatedProject.getTaskList(), new Project.SortList());
            Collections.sort(projectFromDB.getTaskList(), new Project.SortList());
            if(!updatedProject.getTaskList().equals(projectFromDB.getTaskList())) {
                projectFromDB.setTaskList(updatedProject.getTaskList());
            }
        }
        return projectFromDB;
    }
}
