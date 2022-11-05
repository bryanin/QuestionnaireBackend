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
    @JoinColumn(table = "projects", name = "project_id", referencedColumnName = "id")
    private Long projectId;
    @JoinColumn(table = "tasks", name = "task_id", referencedColumnName = "id")
    private Long taskId;
    @Column(name = "file_path")
    private String filePath;

    public ProjectsFiles() {
    }

    public ProjectsFiles(Long id, Long projectId, Long taskId, String filePath) {
        this.id = id;
        this.projectId = projectId;
        this.taskId = taskId;
        this.filePath = filePath;
    }


}
