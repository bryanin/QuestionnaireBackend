package ru.bryanin.dev.questionnairebackend.office.model.user;

import lombok.Data;
import ru.bryanin.dev.questionnairebackend.office.model.task.Comment;
import ru.bryanin.dev.questionnairebackend.office.security.AccessStatus;
import ru.bryanin.dev.questionnairebackend.office.security.SecurityRole;
import ru.bryanin.dev.questionnairebackend.office.model.task.Task;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee extends AbstractUser {

    @Column(name = "position")
    @Enumerated(value = EnumType.STRING)
    private EmployeePosition employeePosition;
    @OneToMany(mappedBy = "tasks", cascade = CascadeType.ALL, orphanRemoval = true)
    @Transient
    private List<Task> tasks;
    @OneToMany(mappedBy = "comments", cascade = CascadeType.ALL, orphanRemoval = true)
    @Transient
    private List<Comment> taskComments;

    public Employee() {
    }

    public Employee(String email, String firstName, String lastName, Long companyId, String phone, String password, EmployeePosition employeePosition, SecurityRole securityRole, AccessStatus accessStatus, List<Task> tasks, List<Comment> taskComments) {
        super(email, firstName, lastName, companyId, phone, password, securityRole, accessStatus);
        this.employeePosition = employeePosition;
        this.tasks = tasks;
        this.taskComments = taskComments;
    }

    public Employee(Long id, String email, String firstName, String lastName, Long companyId, String phone, String password, EmployeePosition employeePosition, SecurityRole securityRole, AccessStatus accessStatus, List<Task> tasks, List<Comment> taskComments) {
        super(id, email, firstName, lastName, companyId, phone, password, securityRole, accessStatus);
        this.employeePosition = employeePosition;
        this.tasks = tasks;
        this.taskComments = taskComments;
    }

    public EmployeePosition getEmployeePosition() {
        return employeePosition;
    }

    public void setEmployeePosition(EmployeePosition employeePosition) {
        this.employeePosition = employeePosition;
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