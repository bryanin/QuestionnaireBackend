package ru.bryanin.dev.questionnairebackend.office.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bryanin.dev.questionnairebackend.office.model.user.Employee;
import ru.bryanin.dev.questionnairebackend.office.repository.EmployeeRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllUsers() {
        return employeeRepository.findAll();
    }

    public Employee getUser(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new IllegalStateException("Сотрудник с id = " + id + " не существует"));
    }

    public Employee getBasicUserByEmail(String email) {
        return employeeRepository.findBasicUserByEmail(email).orElseThrow(() -> new IllegalStateException("Сотрудник с email = " + email + " не существует"));
    }

    public Employee addNewUser(Employee employee) {
        Optional<Employee> basicUserOptional = employeeRepository.findBasicUserByEmail(employee.getEmail());
        if(basicUserOptional.isPresent()) {
            throw new IllegalStateException("Сотрудник с данным email уже зарегистрирован");
        }
        employeeRepository.save(employee);
        return employeeRepository.findBasicUserByEmail(employee.getEmail()).get();
    }

    public void deleteUser(Long id) {
        boolean exists = employeeRepository.existsById(id);
        if(!exists) {
            throw new IllegalStateException("Сотрудник с id = " + id + " не существует");
        }
        employeeRepository.deleteById(id);
    }

    @Transactional
    public Employee updateUser(Long id, Employee employee) {
        Employee employeeFromDB = employeeRepository.findById(id).orElseThrow(() -> new IllegalStateException("Сотрудник с id = " + id + " не существует"));

        if(employee.getEmail() != null && employee.getEmail().length() > 0 && !Objects.equals(employee.getEmail(), employeeFromDB.getEmail())) {
            Optional<Employee> basicUserOptional = employeeRepository.findBasicUserByEmail(employee.getEmail());
            if(basicUserOptional.isPresent()) {
                throw new IllegalStateException("Сотрудник с данным email уже зарегистрирован");
            }
            employeeFromDB.setEmail(employee.getEmail());
        }
        if(employee.getFirstName() != null && employee.getFirstName().length() > 0 && !Objects.equals(employee.getFirstName(), employeeFromDB.getFirstName())) {
            employeeFromDB.setFirstName(employee.getFirstName());
        }
        if(employee.getLastName() != null && employee.getLastName().length() > 0 && !Objects.equals(employee.getLastName(), employeeFromDB.getLastName())) {
            employeeFromDB.setLastName(employee.getLastName());
        }
        if(employee.getPhone() != null && employee.getPhone().length() > 0 && !Objects.equals(employee.getPhone(), employeeFromDB.getPhone())) {
            employeeFromDB.setPhone(employee.getPhone());
        }
        return employeeFromDB;
    }
}
