package ru.bryanin.dev.questionnairebackend.office.model.user;

import lombok.Data;
import ru.bryanin.dev.questionnairebackend.office.model.company.Company;
import ru.bryanin.dev.questionnairebackend.office.security.AccessStatus;
import ru.bryanin.dev.questionnairebackend.office.security.SecurityRole;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer extends AbstractUser {

    @Column(name = "position")
    @Enumerated(value = EnumType.STRING)
    private CustomerPosition customerPosition;
    @ManyToMany
    private List<Company> relatedCompanies;

    public Customer() {
    }

    public Customer(Long id, String email, String firstName, String lastName, Long companyId, String phone, String password, CustomerPosition customerPosition, SecurityRole securityRole, AccessStatus accessStatus, List<Company> relatedCompanies) {
        super(id, email, firstName, lastName, companyId, phone, password, securityRole, accessStatus);
        this.customerPosition = customerPosition;
        this.relatedCompanies = relatedCompanies;
    }

    public CustomerPosition getCustomerPosition() {
        return customerPosition;
    }

    public void setCustomerPosition(CustomerPosition customerPosition) {
        this.customerPosition = customerPosition;
    }

    public List<Company> getRelatedCompanies() {
        return relatedCompanies;
    }

    public void setRelatedCompanies(List<Company> relatedCompanies) {
        this.relatedCompanies = relatedCompanies;
    }
}
