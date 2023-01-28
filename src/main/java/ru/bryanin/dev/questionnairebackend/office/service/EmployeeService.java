package ru.bryanin.dev.questionnairebackend.office.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.bryanin.dev.questionnairebackend.office.dto.user.EmployeeDTO;
import ru.bryanin.dev.questionnairebackend.office.entity.user.Employee;
import ru.bryanin.dev.questionnairebackend.office.entity.user.EmployeePosition;
import ru.bryanin.dev.questionnairebackend.office.repository.EmployeeRepository;
import ru.bryanin.dev.questionnairebackend.office.security.AccessStatus;
import ru.bryanin.dev.questionnairebackend.office.security.SecurityRole;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final CompanyService companyService;
    private final PasswordEncoder passwordEncoder;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper, CompanyService companyService) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.companyService = companyService;
        this.passwordEncoder = new BCryptPasswordEncoder(12);
    }

    private EmployeeDTO convertToDTO(Employee employee) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.typeMap(Employee.class, EmployeeDTO.class).addMapping(Employee::getPositionTitle, EmployeeDTO::setPositionTitle);
        EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
        employeeDTO.setCompanyTitle(companyService.getCompany(employee.getCompanyId()).getTitleShort());
        employeeDTO.setPassword(employee.getPassword());
        employeeDTO.setAccessStatus(employee.getAccessStatus());
        return employeeDTO;
    }

    private Employee convertToEntity(EmployeeDTO employeeDTO) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.typeMap(EmployeeDTO.class, Employee.class).addMapping(EmployeeDTO::getSecurityRole, Employee::setSecurityRole);
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        employee.setSecurityRole(SecurityRole.valueOf(employeeDTO.getSecurityRole()));
        employee.setPosition(employeeDTO.getPosition());
        employee.setAccessStatus(employeeDTO.getAccessStatus());
        return employee;
    }

    public List<EmployeeDTO> getAllEmployeesDTO() {
        List<EmployeeDTO> employeeDTOList = employeeRepository.findAll().stream().map(employee -> convertToDTO(employee)).collect(Collectors.toList());
        return employeeDTOList;
    }

    public EmployeeDTO getEmployeeDTO(Long id) {
        return convertToDTO(employeeRepository.findById(id).orElseThrow(() -> new IllegalStateException("Сотрудник с id = " + id + " не существует")));
    }

    public Employee getEmployeeByEmail(String email) {
        return employeeRepository.findBasicUserByEmail(email).orElseThrow(() -> new IllegalStateException("Сотрудник с email = " + email + " не существует"));
    }

    public Employee addNewEmployeeDTO(EmployeeDTO employeeDTO) {
        //------------ Проверка на дубль и корректность email -----------------//
        Optional<Employee> basicUserOptional = employeeRepository.findBasicUserByEmail(employeeDTO.getEmail());
        if(basicUserOptional.isPresent()) {
            throw new IllegalStateException("Сотрудник с данным email уже зарегистрирован");
        }
        // Добавить проверку по regex
//        String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$";
//        Pattern emailPattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
//        Matcher emailMatcher = emailPattern.matcher(employeeDTO.getEmail().toUpperCase());
//        boolean x = emailMatcher.find();
//        if(!emailMatcher.find()) {
//            throw new IllegalStateException("Email = " + employeeDTO.getEmail() + " не корректен");
//        }
        //------------ Проверка на наличие и корректность password -----------------//
        // Добавить проверку по regex
        Optional<String> optionalPassword = Optional.ofNullable(employeeDTO.getPassword());
        if(!optionalPassword.isPresent() || !optionalPassword.get().equals("")) {
        } else {
            throw new IllegalStateException("Пароль не может быть пустым");
        }
        String rawPassword = employeeDTO.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        employeeDTO.setPassword(encodedPassword);
        //------------ Проверка имени и фамилии на корректность -----------------//
        // Добавить проверку по regex
//        String nameRegex = "^[А-Яа-я]{1,50}$";
//        Pattern namePattern = Pattern.compile(nameRegex);
//        Matcher nameMatcher1 = namePattern.matcher(employeeDTO.getFirstName());
//        if(!nameMatcher1.matches()) {
//            throw new IllegalStateException("Имя = " + employeeDTO.getFirstName() + " указано не корректно");
//        }
//        Matcher nameMatcher2 = namePattern.matcher(employeeDTO.getLastName());
//        if(!nameMatcher2.matches()) {
//            throw new IllegalStateException("Фамилия = " + employeeDTO.getLastName() + " указана не корректно");
//        }
        //------------ Проверка телефона -----------------//
        // Добавить проверку по regex
        //------------ Установка статуса -----------------//
        employeeDTO.setAccessStatus(AccessStatus.ACTIVE);
        //------------ Конвертация в DTO -----------------//
        Employee employee = convertToEntity(employeeDTO);
        //------------ Запись в БД -----------------//
        employeeRepository.save(employee);
        return employeeRepository.findBasicUserByEmail(employee.getEmail()).get();
    }

    public void deleteEmployeeDTO(Long id) {
        boolean exists = employeeRepository.existsById(id);
        if(!exists) {
            throw new IllegalStateException("Сотрудник с id = " + id + " не существует");
        }
        employeeRepository.deleteById(id);
    }

    @Transactional
    public EmployeeDTO updateEmployeeDTO(Long id, EmployeeDTO employeeDTO) {
        Employee employeeFromDB = employeeRepository.findById(id).orElseThrow(() -> new IllegalStateException("Сотрудник с id = " + id + " не существует"));
        //------------ Проверка email -----------------//
        // Добавить проверку по regex
        String employeeDTOEmail = employeeDTO.getEmail();
        if(employeeDTOEmail != null && employeeDTOEmail.length() > 0) {
            if(!Objects.equals(employeeDTOEmail, employeeFromDB.getEmail())) {
                Optional<Employee> employeeOptional = employeeRepository.findBasicUserByEmail(employeeDTOEmail);
                if(employeeOptional.isPresent()) {
                    throw new IllegalStateException("Сотрудник с данным email уже зарегистрирован");
                }
                employeeFromDB.setEmail(employeeDTOEmail);
            }
        } else {
            throw new IllegalStateException("Email должен быть указан");
        }
        //------------ Проверка имени и фамилии -----------------//
        // Добавить проверку по regex
        if(employeeDTO.getFirstName() != null && employeeDTO.getFirstName().length() > 0) {
            if(!Objects.equals(employeeDTO.getFirstName(), employeeFromDB.getFirstName())) {
                employeeFromDB.setFirstName(employeeDTO.getFirstName());
            }
        } else {
            throw new IllegalStateException("Не указано имя пользователя");
        }
        if(employeeDTO.getLastName() != null && employeeDTO.getLastName().length() > 0) {
            if(!Objects.equals(employeeDTO.getLastName(), employeeFromDB.getLastName())) {
                employeeFromDB.setLastName(employeeDTO.getLastName());
            }
        } else {
            throw new IllegalStateException("Не указана фамилия пользователя");
        }
        //------------ Проверка телефона -----------------//
        // Добавить проверку по regex
        if(employeeDTO.getPhone() != null && employeeDTO.getPhone().length() > 0 && !Objects.equals(employeeDTO.getPhone(), employeeFromDB.getPhone())) {
            employeeFromDB.setPhone(employeeDTO.getPhone());
        }
        //------------ Проверка должности -----------------//
        if(employeeDTO.getPosition() != null && employeeDTO.getPosition().getTitle().length() > 0) {
            if(!Objects.equals(employeeDTO.getPosition(), employeeFromDB.getPosition())) {
                employeeFromDB.setPosition(employeeDTO.getPosition());
            }
        } else {
            throw new IllegalStateException("Должность должна быть указана");
        }
        //------------ Проверка статуса -----------------//
        if(!employeeDTO.getAccessStatus().equals(employeeFromDB.getAccessStatus())) {
            employeeFromDB.setAccessStatus(employeeDTO.getAccessStatus());
        }
        //------------ Конвертация в DTO -----------------//
        return convertToDTO(employeeFromDB);
    }

    public Map<String, String> getAllEmployeesPositions() {
        /////// LIST ////////
        List<EmployeePosition> allEmployeePositionsList = Arrays.asList(EmployeePosition.values());
        Map<String, String> allEmployeePositionsMap = new HashMap<>();
        allEmployeePositionsList.stream().forEach(position -> {
            allEmployeePositionsMap.put(position.name(), position.getTitle());
        });
        return allEmployeePositionsMap;
    }
}
