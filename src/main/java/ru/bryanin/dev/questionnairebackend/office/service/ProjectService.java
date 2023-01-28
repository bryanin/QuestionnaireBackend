package ru.bryanin.dev.questionnairebackend.office.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.bryanin.dev.questionnairebackend.office.dto.project.ProjectDTO;
import ru.bryanin.dev.questionnairebackend.office.dto.task.TaskDTO;
import ru.bryanin.dev.questionnairebackend.office.entity.project.Address;
import ru.bryanin.dev.questionnairebackend.office.entity.project.Project;
import ru.bryanin.dev.questionnairebackend.office.entity.task.Task;
import ru.bryanin.dev.questionnairebackend.office.entity.user.Employee;
import ru.bryanin.dev.questionnairebackend.office.repository.AddressRepository;
import ru.bryanin.dev.questionnairebackend.office.repository.EmployeeRepository;
import ru.bryanin.dev.questionnairebackend.office.repository.ProjectRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;
    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;
    private final EmployeeService employeeService;
    private final AddressService addressService;
    private final DateTimeFormatter formatter;

    public ProjectService(ProjectRepository projectRepository, EmployeeRepository employeeRepository, AddressRepository addressRepository, ModelMapper modelMapper, EmployeeService employeeService, AddressService addressService) {
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
        this.employeeService = employeeService;
        this.addressService = addressService;
        this.formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    }

    private ProjectDTO convertToDTO(Project project) {
        ProjectDTO projectDTO = modelMapper.map(project, ProjectDTO.class);
        projectDTO.setOwnerLastName(employeeService.getEmployeeByEmail(project.getOwnerEmail()).getLastName());
        projectDTO.setOwnerFirstName(employeeService.getEmployeeByEmail(project.getOwnerEmail()).getFirstName());
        Address address = project.getAddress();
        projectDTO.setAddressIdToString(address.getId().toString());
        Optional<Integer> optionalPostalCode = Optional.ofNullable(address.getPostalCode());
        if (optionalPostalCode.isPresent()) {
            projectDTO.setAddressPostalCodeToString(optionalPostalCode.get().toString());
        } else {
            projectDTO.setAddressPostalCodeToString(null);
        }
        Optional<String> optionalCountry = Optional.ofNullable(address.getCountry());
        if(optionalCountry.isPresent()) {
            projectDTO.setAddressCountryToString(optionalCountry.get());
        } else {
            projectDTO.setAddressCountryToString(null);
        }
        Optional<String> optionalRegion = Optional.ofNullable(address.getRegion());
        if(optionalRegion.isPresent()) {
            projectDTO.setAddressRegionToString(optionalRegion.get());
        } else {
            projectDTO.setAddressCityToString(null);
        }
        Optional<String> optionalCity = Optional.ofNullable(address.getCity());
        if(optionalCity.isPresent()) {
            projectDTO.setAddressCityToString(optionalCity.get());
        } else {
            projectDTO.setAddressCityToString(null);
        }
        Optional<String> optionalSettlement = Optional.ofNullable(address.getSettlement());
        if(optionalSettlement.isPresent()) {
            projectDTO.setAddressSettlementToString(optionalSettlement.get());
        } else {
            projectDTO.setAddressSettlementToString(null);
        }
        Optional<String> optionalStreet = Optional.ofNullable(address.getStreet());
        if(optionalStreet.isPresent()) {
            projectDTO.setAddressStreetToString(optionalStreet.get());
        } else {
            projectDTO.setAddressStreetToString(null);
        }
        Optional<String> optionalHouse = Optional.ofNullable(address.getHouse());
        if(optionalHouse.isPresent()) {
            projectDTO.setAddressHouseToString(optionalHouse.get());
        } else {
            projectDTO.setAddressHouseToString(null);
        }
        Optional<String> optionalBlock = Optional.ofNullable(address.getBlock());
        if(optionalBlock.isPresent()) {
            projectDTO.setAddressBlockToString(optionalBlock.get());
        } else {
            projectDTO.setAddressBlockToString(null);
        }
        projectDTO.setCreatedAt(project.getCreatedAt().format(formatter));
        projectDTO.setStatus(project.getStatus().name());
        return projectDTO;
    }

    private Project convertToEntity(ProjectDTO projectDTO) {
        Project project = modelMapper.map(projectDTO, Project.class);
        Optional<String> optionalCreatedAt = Optional.ofNullable(projectDTO.getCreatedAt());
        if(optionalCreatedAt.isPresent()) {
            project.setCreatedAt(LocalDate.parse(projectDTO.getCreatedAt(), formatter));
        } else {
            project.setCreatedAt(LocalDate.now());
        }
        Address address = null;
        Optional<String> optionalAddressId = Optional.ofNullable(projectDTO.getAddressIdToString());
        if(optionalAddressId.isPresent() && !optionalAddressId.get().isEmpty()) {
            try {
                Long.valueOf(optionalAddressId.get());
            } catch (NumberFormatException e) {
                throw new IllegalStateException("id адреса указан, но некорректен: " + optionalAddressId.get());
            }
            address = addressRepository.findById(Long.valueOf(optionalAddressId.get())).orElseThrow(() -> new IllegalStateException("Адрес с id = " + optionalAddressId.get() + " не существует"));
        } else {
            try {
                Long.valueOf(projectDTO.getAddressPostalCodeToString());
            } catch (NumberFormatException e) {
                throw new IllegalStateException("Почтовый индекс адреса указан, но некорректен: " + projectDTO.getAddressPostalCodeToString());
            }
            Optional<Address> optionalAddress = addressRepository.findIfExist(
                    Integer.parseInt(projectDTO.getAddressPostalCodeToString()),
                    projectDTO.getAddressCountryToString(),
                    projectDTO.getAddressRegionToString(),
                    projectDTO.getAddressCityToString(),
                    projectDTO.getAddressSettlementToString(),
                    projectDTO.getAddressStreetToString(),
                    projectDTO.getAddressHouseToString(),
                    projectDTO.getAddressBlockToString()
            );
            if(optionalAddress.isPresent()) {
                address = optionalAddress.get();
            } else {
                address = new Address();
                if(!projectDTO.getAddressPostalCodeToString().isEmpty() && projectDTO.getAddressPostalCodeToString().length() > 0) {
                    address.setPostalCode(Integer.parseInt(projectDTO.getAddressPostalCodeToString()));
                }
                if(!projectDTO.getAddressCountryToString().isEmpty() && projectDTO.getAddressCountryToString().length() > 0) {
                    address.setCountry(projectDTO.getAddressCountryToString());
                }
                if(!projectDTO.getAddressRegionToString().isEmpty() && projectDTO.getAddressRegionToString().length() > 0) {
                    address.setRegion(projectDTO.getAddressRegionToString());
                }
                if(!projectDTO.getAddressCityToString().isEmpty() && projectDTO.getAddressCityToString().length() > 0) {
                    address.setCity(projectDTO.getAddressCityToString());
                }
                if(!projectDTO.getAddressSettlementToString().isEmpty() && projectDTO.getAddressSettlementToString().length() > 0) {
                    address.setSettlement(projectDTO.getAddressSettlementToString());
                }
                if(!projectDTO.getAddressStreetToString().isEmpty() && projectDTO.getAddressStreetToString().length() > 0) {
                    address.setStreet(projectDTO.getAddressStreetToString());
                }
                if(!projectDTO.getAddressHouseToString().isEmpty() && projectDTO.getAddressHouseToString().length() > 0) {
                    address.setHouse(projectDTO.getAddressHouseToString());
                }
                if(!projectDTO.getAddressBlockToString().isEmpty() && projectDTO.getAddressBlockToString().length() > 0) {
                    address.setBlock(projectDTO.getAddressBlockToString());
                }
                addressRepository.save(address);
            }

        }
        project.setAddress(address);
        project.setOwnerEmail(projectDTO.getOwnerEmail());


        return project;
    }

    public List<ProjectDTO> getAllProjectsDTO() {
        return projectRepository.findAll().stream().map(project -> convertToDTO(project)).collect(Collectors.toList());
    }

    public ProjectDTO getProjectDTO(Long id) {
        return convertToDTO(projectRepository.findById(id).orElseThrow(() -> new IllegalStateException("Проекта с id = " + id + " не существует")));
    }

    public ProjectDTO addNewProjectDTO(ProjectDTO newProjectDTO) {
        String newProjectTitle = newProjectDTO.getTitle();
        Optional<Project> optionalProjectByTitle = projectRepository.findByTitle(newProjectTitle);
        if(optionalProjectByTitle.isPresent()) {
            throw new IllegalStateException("Проект с названием " + newProjectTitle + " уже зарегистрирован");
        }
        String newProject1Cid = newProjectDTO.getId_1C();
        Optional<Project> optionalProjectBy1CId = projectRepository.findBy1CId(newProject1Cid);
        if(optionalProjectBy1CId.isPresent()) {
            throw new IllegalStateException("Проект 1C " + newProject1Cid + " уже привязан к проекту " + optionalProjectBy1CId.get().getTitle());
        }
        String newProjectOwnerEmail = newProjectDTO.getOwnerEmail();
        Optional<Employee> optionalProjectByEmail = employeeRepository.findBasicUserByEmail(newProjectOwnerEmail);
        if(!optionalProjectByEmail.isPresent()) {
            throw new IllegalStateException("Проект не может быть сохранен, т.к. пользователь с email " + newProjectOwnerEmail + " не зарегистрирован");
        }
        if(newProjectDTO.getCreatedAt() == null) {
            newProjectDTO.setCreatedAt(LocalDate.now().format(formatter));
        }
        newProjectDTO.setOwnerLastName(employeeService.getEmployeeByEmail(newProjectDTO.getOwnerEmail()).getLastName());
        newProjectDTO.setOwnerFirstName(employeeService.getEmployeeByEmail(newProjectDTO.getOwnerEmail()).getFirstName());
        newProjectDTO.setStatus(Project.Status.ARCHIVED.name());
        try {
            Integer.parseInt(newProjectDTO.getAddressPostalCodeToString());
        } catch (NumberFormatException e) {
            throw new IllegalStateException("Почтовый индекс адреса указан, но некорректен: " + newProjectDTO.getAddressPostalCodeToString());
        }
//        Optional<Address> optionalAddress = addressRepository.findIfExist(
//                Integer.parseInt(newProjectDTO.getAddressPostalCodeToString()),
//                newProjectDTO.getAddressCountryToString(),
//                newProjectDTO.getAddressRegionToString(),
//                newProjectDTO.getAddressCityToString(),
//                newProjectDTO.getAddressSettlementToString(),
//                newProjectDTO.getAddressStreetToString(),
//                newProjectDTO.getAddressHouseToString(),
//                newProjectDTO.getAddressBlockToString()
//        );
//        if(optionalAddress.isPresent()) {
//
//        }
        projectRepository.save(convertToEntity(newProjectDTO));
        return convertToDTO(projectRepository.findByTitle(newProjectDTO.getTitle()).get());
    }

    public void deleteProjectDTO(Long id) {
        boolean exists = projectRepository.existsById(id);
        if(!exists) {
            throw new IllegalStateException("Проекта с id = " + id + " не существует");
        }
        projectRepository.deleteById(id);
    }

    @Transactional
    public ProjectDTO updateProjectDTO(Long id, ProjectDTO updatedProjectDTO) {

        //    -    this.projectsPartners = projectsPartners;

        Project projectFromDB = projectRepository.findById(id).orElseThrow(() -> new IllegalStateException("Проекта с id = " + id + " не существует"));

        if(updatedProjectDTO.getId() == null) {
            throw new IllegalStateException("Проект должен иметь id");
        }

        if(updatedProjectDTO.getId_1C() != null && updatedProjectDTO.getId_1C().length() > 0 && !Objects.equals(updatedProjectDTO.getId_1C(), projectFromDB.getId_1C())) {
            Optional<Project> projectOptional = projectRepository.findBy1CId(updatedProjectDTO.getId_1C());
            if(projectOptional.isPresent()) {
                throw new IllegalStateException("Проект 1C " + projectOptional.get().getId_1C() + " уже привязан к проекту " + projectOptional.get().getTitle());
            }
            projectFromDB.setId_1C(updatedProjectDTO.getId_1C());
        }

        if(updatedProjectDTO.getTitle() != null && updatedProjectDTO.getTitle().length() > 0) {
            if(!Objects.equals(updatedProjectDTO.getTitle(), projectFromDB.getTitle()))
            projectFromDB.setTitle(updatedProjectDTO.getTitle());
        } else {
            throw new IllegalStateException("Проект должен иметь название");
        }

        if(updatedProjectDTO.getDescription() != null && updatedProjectDTO.getDescription().length() > 0 && !Objects.equals(updatedProjectDTO.getDescription(), projectFromDB.getDescription())) {
            projectFromDB.setDescription(updatedProjectDTO.getDescription());
        }

        if(updatedProjectDTO.getOwnerEmail() != null) {
            if(!Objects.equals(updatedProjectDTO.getOwnerEmail(), projectFromDB.getOwnerEmail())) {
                projectFromDB.setOwnerEmail(updatedProjectDTO.getOwnerEmail());
            }
        } else {
            throw new IllegalStateException("В проекте должен быть указан инициатор");
        }


//        if(updatedProjectDTO.getAddress() != null && !Objects.equals(updatedProjectDTO.getAddress(), projectFromDB.getAddress())) {
//            Optional<Address> addressOptional = addressRepository.findIfExist(
//                    updatedProjectDTO.getAddress().getPostalCode(),
//                    updatedProjectDTO.getAddress().getCountry(),
//                    updatedProjectDTO.getAddress().getRegion(),
//                    updatedProjectDTO.getAddress().getCity(),
//                    updatedProjectDTO.getAddress().getSettlement(),
//                    updatedProjectDTO.getAddress().getStreet(),
//                    updatedProjectDTO.getAddress().getHouse(),
//                    updatedProjectDTO.getAddress().getBlock());
//            if(addressOptional.isPresent()) {
//                projectFromDB.setAddress(addressOptional.get());
//            } else {
//                Address newAddress = new Address(
//                        updatedProjectDTO.getAddress().getPostalCode(),
//                        updatedProjectDTO.getAddress().getCountry(),
//                        updatedProjectDTO.getAddress().getRegion(),
//                        updatedProjectDTO.getAddress().getCity(),
//                        updatedProjectDTO.getAddress().getSettlement(),
//                        updatedProjectDTO.getAddress().getStreet(),
//                        updatedProjectDTO.getAddress().getHouse(),
//                        updatedProjectDTO.getAddress().getBlock());
//                addressRepository.save(newAddress);
//                projectFromDB.setAddress(newAddress);
//            }
//
//        }

        if(updatedProjectDTO.getStatus() != null) {
            if(!Objects.equals(updatedProjectDTO.getStatus(), projectFromDB.getStatus())) {
                if(Arrays.stream(Project.Status.values()).anyMatch(status -> status.name().equals(updatedProjectDTO.getStatus()))) {
                    projectFromDB.setStatus(Project.Status.valueOf(updatedProjectDTO.getStatus()));
                } else {
                    throw new IllegalStateException("В проекте указан некорректный статус");
                }
            }
        } else {
            throw new IllegalStateException("В проекте должен быть указан статус");
        }

        return convertToDTO(projectFromDB);

    }

    public List<Long> quantityOfNewProjects() {
        List<Long> list = new ArrayList<>();
        list.add(Long.valueOf(projectRepository.findAll().size()));
        list.add(projectRepository.findAll().stream().filter(project -> project.getCreatedAt().isAfter(LocalDate.now().minusDays(7))).count());
        return  list;
    }
}
