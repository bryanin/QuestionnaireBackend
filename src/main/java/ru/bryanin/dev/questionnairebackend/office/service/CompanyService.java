package ru.bryanin.dev.questionnairebackend.office.service;

import org.springframework.stereotype.Service;
import ru.bryanin.dev.questionnairebackend.office.model.company.Company;
import ru.bryanin.dev.questionnairebackend.office.model.user.Customer;
import ru.bryanin.dev.questionnairebackend.office.repository.CompanyRepository;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompany(Long id) {
        return companyRepository.findById(id).orElseThrow(() -> new IllegalStateException("Компания с id = " + id + " не существует"));
    }

    public String getCompanyShortTitleById(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new IllegalStateException("Компания с id = " + id + " не существует"));
        return company.getTitleShort();
    }
}
