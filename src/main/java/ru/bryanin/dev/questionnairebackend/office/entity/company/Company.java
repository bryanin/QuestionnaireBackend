package ru.bryanin.dev.questionnairebackend.office.entity.company;

import lombok.Data;
import ru.bryanin.dev.questionnairebackend.office.entity.project.ProjectsPartners;
import ru.bryanin.dev.questionnairebackend.office.entity.user.Customer;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "companies")
public class Company {
    @Id
    @SequenceGenerator(name = "companies_sequence", sequenceName = "companies_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "companies_sequence")
    private Long id;
    @Column(name = "inn", nullable = false, unique = true)
    private String inn;
    @Column(name = "title_short")
    private String titleShort;
    @Column(name = "title_full")
    private String titleFull;
    @Transient
    @OneToMany (mappedBy = "employees, customers", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Customer> employees;
    @Transient
    @OneToMany (mappedBy = "projects_partners", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProjectsPartners> projectsPartners;

    public Company() {
    }

    public Company(String inn, String titleShort, String titleFull, List<Customer> employees) {
        this.inn = inn;
        this.titleShort = titleShort;
        this.titleFull = titleFull;
        this.employees = employees;
    }

    public Company(Long id, String inn, String titleShort, String titleFull, List<Customer> employees) {
        this.id = id;
        this.inn = inn;
        this.titleShort = titleShort;
        this.titleFull = titleFull;
        this.employees = employees;
    }
}
