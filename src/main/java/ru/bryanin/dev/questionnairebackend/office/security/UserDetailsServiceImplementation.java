package ru.bryanin.dev.questionnairebackend.office.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.bryanin.dev.questionnairebackend.office.entity.user.Employee;
import ru.bryanin.dev.questionnairebackend.office.repository.EmployeeRepository;

@Service("userDetailsServiceImplementation")
public class UserDetailsServiceImplementation implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public UserDetailsServiceImplementation(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findBasicUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return SecurityUser.fromBasicUser(employee);
    }
}
