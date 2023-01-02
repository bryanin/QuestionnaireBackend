package ru.bryanin.dev.questionnairebackend.office.model.user;

import ru.bryanin.dev.questionnairebackend.office.security.AccessStatus;
import ru.bryanin.dev.questionnairebackend.office.security.SecurityRole;

import javax.persistence.*;

//@Entity
@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public class AbstractUser {

    @Id
    @SequenceGenerator(name = "users_sequence", sequenceName = "users_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_sequence")
    @Column(name = "id")
    private Long id;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @JoinColumn(table = "companies", name = "company_id", referencedColumnName = "id")
    private Long companyId;
    @Column(name = "phone")
    private String phone;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "security_role", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private SecurityRole securityRole;
    @Column(name = "access_status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private AccessStatus accessStatus;

    public AbstractUser() {
    }

    public AbstractUser(String email, String firstName, String lastName, Long companyId, String phone, String password, SecurityRole securityRole, AccessStatus accessStatus) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyId = companyId;
        this.phone = phone;
        this.password = password;
        this.securityRole = securityRole;
        this.accessStatus = accessStatus;
    }

    public AbstractUser(Long id, String email, String firstName, String lastName, Long companyId, String phone, String password, SecurityRole securityRole, AccessStatus accessStatus) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyId = companyId;
        this.phone = phone;
        this.password = password;
        this.securityRole = securityRole;
        this.accessStatus = accessStatus;
    }

    public Long getId() {
        return id;
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

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
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
