package ru.bryanin.dev.questionnairebackend.office.service;

import org.springframework.stereotype.Service;
import ru.bryanin.dev.questionnairebackend.office.entity.user.Customer;
import ru.bryanin.dev.questionnairebackend.office.repository.CustomerRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllUsers() {
        return customerRepository.findAll();
    }

    public Customer getUser(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new IllegalStateException("Клиент с id = " + id + " не существует"));
    }

    public Customer addNewUser(Customer customer) {
        Optional<Customer> basicUserOptional = customerRepository.findBasicUserByEmail(customer.getEmail());
        if(basicUserOptional.isPresent()) {
            throw new IllegalStateException("Клиент с данным email уже зарегистрирован");
        }
        customerRepository.save(customer);
        return customerRepository.findBasicUserByEmail(customer.getEmail()).get();
    }

    public void deleteUser(Long id) {
        boolean exists = customerRepository.existsById(id);
        if(!exists) {
            throw new IllegalStateException("Клиент с id = " + id + " не существует");
        }
        customerRepository.deleteById(id);
    }

    @Transactional
    public Customer updateUser(Long id, Customer customer) {
        Customer customerFromDB = customerRepository.findById(id).orElseThrow(() -> new IllegalStateException("Клиент с id = " + id + " не существует"));

        if(customer.getEmail() != null && customer.getEmail().length() > 0 && !Objects.equals(customer.getEmail(), customerFromDB.getEmail())) {
            Optional<Customer> basicUserOptional = customerRepository.findBasicUserByEmail(customer.getEmail());
            if(basicUserOptional.isPresent()) {
                throw new IllegalStateException("Клиент с данным email уже зарегистрирован");
            }
            customerFromDB.setEmail(customer.getEmail());
        }
        if(customer.getFirstName() != null && customer.getFirstName().length() > 0 && !Objects.equals(customer.getFirstName(), customerFromDB.getFirstName())) {
            customerFromDB.setFirstName(customer.getFirstName());
        }
        if(customer.getLastName() != null && customer.getLastName().length() > 0 && !Objects.equals(customer.getLastName(), customerFromDB.getLastName())) {
            customerFromDB.setLastName(customer.getLastName());
        }
        if(customer.getPhone() != null && customer.getPhone().length() > 0 && !Objects.equals(customer.getPhone(), customerFromDB.getPhone())) {
            customerFromDB.setPhone(customer.getPhone());
        }
        return customerFromDB;
    }





}
