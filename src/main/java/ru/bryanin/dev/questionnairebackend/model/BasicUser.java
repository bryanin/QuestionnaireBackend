package ru.bryanin.dev.questionnairebackend.model;

import ru.bryanin.dev.questionnairebackend.model.security.AccessStatus;
import ru.bryanin.dev.questionnairebackend.model.security.SecurityRole;
import javax.persistence.*;

@Entity
@Table(name = "users")
//@JsonInclude(JsonInclude.Include.NON_NULL)
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
    private Position position;
    @Column(name = "security_role", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private SecurityRole securityRole;
    @Column(name = "access_status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private AccessStatus accessStatus;


    public BasicUser() {
    }

    public BasicUser(Long id, String email, String firstName, String lastName, String phone) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompanyTitle() {
        return companyTitle;
    }

    public void setCompanyTitle(String companyTitle) {
        this.companyTitle = companyTitle;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public SecurityRole getSecurityRole() {
        return securityRole;
    }

    public void setSecurityRole(SecurityRole securityRole) {
        this.securityRole = securityRole;
    }

    public AccessStatus getAccessStatus() {
        return accessStatus;
    }

    public void setAccessStatus(AccessStatus accessStatus) {
        this.accessStatus = accessStatus;
    }
}