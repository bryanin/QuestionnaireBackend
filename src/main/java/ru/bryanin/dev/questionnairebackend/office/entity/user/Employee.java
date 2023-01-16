package ru.bryanin.dev.questionnairebackend.office.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.bryanin.dev.questionnairebackend.office.dto.user.EmployeeDTO;
import ru.bryanin.dev.questionnairebackend.office.entity.task.Comment;
import ru.bryanin.dev.questionnairebackend.office.security.AccessStatus;
import ru.bryanin.dev.questionnairebackend.office.security.SecurityRole;
import ru.bryanin.dev.questionnairebackend.office.entity.task.Task;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee extends AbstractUser {

    @Column(name = "position")
    @Enumerated(value = EnumType.STRING)
    private EmployeePosition position;
    @OneToMany(mappedBy = "tasks", cascade = CascadeType.ALL, orphanRemoval = true)
    @Transient
    @JsonIgnore
    private List<Task> tasks;
    @OneToMany(mappedBy = "comments", cascade = CascadeType.ALL, orphanRemoval = true)
    @Transient
    @JsonIgnore
    private List<Comment> taskComments;

    public Employee() {
    }

    public Employee(String email, String firstName, String lastName, Long companyId, String phone, String password, EmployeePosition position, SecurityRole securityRole, AccessStatus accessStatus, List<Task> tasks, List<Comment> taskComments) {
        super(email, firstName, lastName, companyId, phone, password, securityRole, accessStatus);
        this.position = position;
        this.tasks = tasks;
        this.taskComments = taskComments;
    }

    public Employee(Long id, String email, String firstName, String lastName, Long companyId, String phone, String password, EmployeePosition position, SecurityRole securityRole, AccessStatus accessStatus, List<Task> tasks, List<Comment> taskComments) {
        super(id, email, firstName, lastName, companyId, phone, password, securityRole, accessStatus);
        this.position = position;
        this.tasks = tasks;
        this.taskComments = taskComments;
    }

    public EmployeePosition getPosition() {
        return position;
    }

    public String getPositionTitle() {
        return position.getTitle();
    }

    public void setPosition(EmployeePosition position) {
        this.position = position;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Comment> getTaskComments() {
        return taskComments;
    }

    public void setTaskComments(List<Comment> taskComments) {
        this.taskComments = taskComments;
    }



}