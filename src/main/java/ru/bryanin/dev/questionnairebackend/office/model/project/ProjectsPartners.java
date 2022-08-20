package ru.bryanin.dev.questionnairebackend.office.model.project;

import lombok.Data;
import ru.bryanin.dev.questionnairebackend.office.model.company.Company;

import javax.persistence.*;

@Data
@Table(name = "projects_partners")
@Entity
public class ProjectsPartners {

    @Id
    @SequenceGenerator(name = "projects_partners_sequence", sequenceName = "projects_partners_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "projects_partners_sequence")
    private Long id;
    @ManyToOne
    private Company company;
    @ManyToOne
    private Project project;
    @Column(name = "partner_role")
    @Enumerated(value = EnumType.STRING)
    private Role role;

    public enum Role {
        END_USER,
        INSTALLER,
        TRADE_COMPANY,
        GENERAL_CONTRACTOR,
        DESIGNER
    }

    public ProjectsPartners() {
    }

    public ProjectsPartners(Long id, Company company, Project project, Role role) {
        this.id = id;
        this.company = company;
        this.project = project;
        this.role = role;
    }
}
