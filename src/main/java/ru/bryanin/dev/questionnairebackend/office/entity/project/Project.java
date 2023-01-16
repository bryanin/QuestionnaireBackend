package ru.bryanin.dev.questionnairebackend.office.entity.project;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.bryanin.dev.questionnairebackend.office.entity.task.Task;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @SequenceGenerator(name = "projects_sequence", sequenceName = "projects_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "projects_sequence")
    private Long id;
    @Column(name = "id_1C")
    private String id_1C;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description")
    private String description;
    @JoinColumn(table = "users", name = "owner_email", referencedColumnName = "email")
    private String ownerEmail;
    @OneToOne
    private Address address;
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;
    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Project.Status status;
    @Transient
    @JsonIgnore
    @OneToMany(mappedBy = "tasks", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> taskList;
    @Transient
    @JsonIgnore
    @OneToMany(mappedBy = "projects_files")
    private List<ProjectsFiles> projectsFiles;
    @Transient
    @JsonIgnore
    @OneToMany (mappedBy = "projects_partners", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProjectsPartners> projectsPartners;
//    Подумать над реализацией
//    @Transient
//    @JsonSerialize
//    private String ownerLastName;
//    @Transient
//    @JsonSerialize
//    private String ownerFirstName;

    public Project() {
    }

    public Project(Long id, String id_1C, String title, String description, String ownerEmail, Address address, LocalDate createdAt, Status status, List<Task> taskList, List<ProjectsFiles> projectsFiles, List<ProjectsPartners> projectsPartners) {
        this.id = id;
        this.id_1C = id_1C;
        this.title = title;
        this.description = description;
        this.ownerEmail = ownerEmail;
        this.address = address;
        this.createdAt = createdAt;
        this.status = status;
        this.taskList = taskList;
        this.projectsFiles = projectsFiles;
        this.projectsPartners = projectsPartners;
    }

    public Long getId() {
        return id;
    }

    public String getId_1C() {
        return id_1C;
    }

    public void setId_1C(String id_1C) {
        this.id_1C = id_1C;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<ProjectsFiles> getProjectsFiles() {
        return projectsFiles;
    }

    public void setProjectsFiles(List<ProjectsFiles> projectsFiles) {
        this.projectsFiles = projectsFiles;
    }

    public List<ProjectsPartners> getProjectsPartners() {
        return projectsPartners;
    }

    public void setProjectsPartners(List<ProjectsPartners> projectsPartners) {
        this.projectsPartners = projectsPartners;
    }

//    Подумать над реализацией
//    public String getOwnerLastName() {
//        return "ownerLastName";
//    }
//
//    public void setOwnerLastName(String ownerLastName) {
//        this.ownerLastName = ownerLastName;
//    }
//
//    public String getOwnerFirstName() {
//        return "ownerFirstName";
//    }
//
//    public void setOwnerFirstName(String ownerFirstName) {
//        this.ownerFirstName = ownerFirstName;
//    }

    public static class SortList implements Comparator<Task> {
        @Override
        public int compare(Task task1, Task task2) {
            return task1.getId().compareTo(task2.getId());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        if (!Objects.equals(id_1C, project.id_1C)) return false;
        if (!title.equals(project.title)) return false;
        if (!Objects.equals(description, project.description)) return false;
        if (!ownerEmail.equals(project.ownerEmail)) return false;
        if (!address.equals(project.address)) return false;
        if (!createdAt.equals(project.createdAt)) return false;
        if (status != project.status) return false;
        if (!Objects.equals(taskList, project.taskList)) return false;
        if (!Objects.equals(projectsFiles, project.projectsFiles))
            return false;
        return Objects.equals(projectsPartners, project.projectsPartners);
    }

    @Override
    public int hashCode() {
        int result = id_1C != null ? id_1C.hashCode() : 0;
        result = 31 * result + title.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + ownerEmail.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + createdAt.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + (taskList != null ? taskList.hashCode() : 0);
        result = 31 * result + (projectsFiles != null ? projectsFiles.hashCode() : 0);
        result = 31 * result + (projectsPartners != null ? projectsPartners.hashCode() : 0);
        return result;
    }

    public enum Status {
        ACTIVE,
        ARCHIVED
    }
}
