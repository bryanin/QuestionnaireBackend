package ru.bryanin.dev.questionnairebackend.office.entity.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Data
@Table(name = "projects_partners")
@Entity
public class ProjectsPartners {

    @Id
    @SequenceGenerator(name = "projects_partners_sequence", sequenceName = "projects_partners_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "projects_partners_sequence")
    private Long id;
    @JoinColumn(table = "companies", name = "company_id", referencedColumnName = "id")
    private Long companyId;
    @JoinColumn(table = "companies", name = "company_title_short", referencedColumnName = "title_short")
    private String companyTitleShort;
    @JoinColumn(table = "projects", name = "project_id", referencedColumnName = "id")
    private Long projectId;
    @Column(name = "partner_role")
    @Enumerated(value = EnumType.STRING)
    private PartnerRole partnerRole;

    @Getter
    @AllArgsConstructor
    public enum PartnerRole {
        END_USER("Заказчик"),
        INSTALLER ("Инсталлятор"),
        TRADE_COMPANY("Торговая компания"),
        GENERAL_CONTRACTOR("Генподрядчик"),
        DESIGNER("Проектировщик");

        public final String description;
    }

    public ProjectsPartners() {
    }

    public ProjectsPartners(Long id, Long companyId, String companyTitleShort, Long projectId, PartnerRole partnerRole) {
        this.id = id;
        this.companyId = companyId;
        this.companyTitleShort = companyTitleShort;
        this.projectId = projectId;
        this.partnerRole = partnerRole;
    }
}
