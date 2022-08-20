package ru.bryanin.dev.questionnairebackend.office.model.project;

import lombok.Data;
import ru.bryanin.dev.questionnairebackend.office.model.task.Task;

import javax.persistence.*;

@Data
@Entity
@Table(name = "projects_files")
public class ProjectsFiles {

    @Id
    @SequenceGenerator(name = "projects_files_sequence", sequenceName = "projects_files_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "projects_files_sequence")
    private Long id;
    @ManyToOne
    private Project project;
    @ManyToOne
    private Task task;
    @Column(name = "file_path")
    private String filePath;

    public ProjectsFiles() {
    }

    public ProjectsFiles(Long id, Project project, Task task, String filePath) {
        this.id = id;
        this.project = project;
        this.task = task;
        this.filePath = filePath;
    }
}
