package ru.bryanin.dev.questionnairebackend.office.model.project;

import lombok.Data;
import ru.bryanin.dev.questionnairebackend.office.model.task.Task;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Data
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
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;
    @Column(name = "project_status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ProjectStatus projectStatus;
    @Transient
    @OneToMany(mappedBy = "tasks", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> taskList;

    public Project() {
    }

    public Project(Long id, String id_1C, String title, String description, String ownerEmail, String city, LocalDate createdAt) {
        this.id = id;
        this.id_1C = id_1C;
        this.title = title;
        this.description = description;
        this.ownerEmail = ownerEmail;
        this.city = city;
        this.createdAt = createdAt;
        this.projectStatus = ProjectStatus.WITHOUT_ACTIVE_TASKS;
    }

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

        if (!id.equals(project.id)) return false;
        if (id_1C != null ? !id_1C.equals(project.id_1C) : project.id_1C != null) return false;
        if (!title.equals(project.title)) return false;
        if (description != null ? !description.equals(project.description) : project.description != null) return false;
        if (ownerEmail != null ? !ownerEmail.equals(project.ownerEmail) : project.ownerEmail != null) return false;
        if (!city.equals(project.city)) return false;
        if (!createdAt.equals(project.createdAt)) return false;
        return taskList != null ? taskList.equals(project.taskList) : project.taskList == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (id_1C != null ? id_1C.hashCode() : 0);
        result = 31 * result + title.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (ownerEmail != null ? ownerEmail.hashCode() : 0);
        result = 31 * result + city.hashCode();
        result = 31 * result + createdAt.hashCode();
        result = 31 * result + (taskList != null ? taskList.hashCode() : 0);
        return result;
    }
}
