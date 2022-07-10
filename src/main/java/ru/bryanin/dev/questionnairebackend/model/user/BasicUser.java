package ru.bryanin.dev.questionnairebackend.model.user;

import lombok.Data;
import ru.bryanin.dev.questionnairebackend.security.AccessStatus;
import ru.bryanin.dev.questionnairebackend.security.SecurityRole;
import ru.bryanin.dev.questionnairebackend.model.task.Task;
import ru.bryanin.dev.questionnairebackend.model.task.TaskComment;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class BasicUser {
    @Id
    @SequenceGenerator(name = "users_sequence", sequenceName = "users_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_sequence")
    private Long id;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "company")
    private String companyTitle;
    @Column(name = "phone")
    private String phone;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "position")
    @Enumerated(value = EnumType.STRING)
    private Position position;
    @Column(name = "security_role", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private SecurityRole securityRole;
    @Column(name = "access_status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private AccessStatus accessStatus;
    @OneToMany(mappedBy = "tasks", cascade = CascadeType.ALL, orphanRemoval = true)
    @Transient
    private List<Task> tasks;
    @OneToMany(mappedBy = "comments", cascade = CascadeType.ALL, orphanRemoval = true)
    @Transient
    private List<TaskComment> taskComments;

    public BasicUser() {
    }

    public BasicUser(Long id, String email, String firstName, String lastName, String companyTitle, String phone, String password, Position position, SecurityRole securityRole, AccessStatus accessStatus) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyTitle = companyTitle;
        this.phone = phone;
        this.password = password;
        this.position = position;
        this.securityRole = securityRole;
        this.accessStatus = accessStatus;
    }
}