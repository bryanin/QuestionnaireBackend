package ru.bryanin.dev.questionnairebackend.office.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.bryanin.dev.questionnairebackend.office.model.company.Company;
import ru.bryanin.dev.questionnairebackend.office.model.project.Address;
import ru.bryanin.dev.questionnairebackend.office.model.project.Project;
import ru.bryanin.dev.questionnairebackend.office.model.project.ProjectsFiles;
import ru.bryanin.dev.questionnairebackend.office.model.project.ProjectsPartners;
import ru.bryanin.dev.questionnairebackend.office.model.task.*;
import ru.bryanin.dev.questionnairebackend.office.model.task.SecuritySystem;
import ru.bryanin.dev.questionnairebackend.office.model.user.Customer;
import ru.bryanin.dev.questionnairebackend.office.model.user.CustomerPosition;
import ru.bryanin.dev.questionnairebackend.office.model.user.Employee;
import ru.bryanin.dev.questionnairebackend.office.model.user.EmployeePosition;
import ru.bryanin.dev.questionnairebackend.office.repository.*;
import ru.bryanin.dev.questionnairebackend.office.security.AccessStatus;
import ru.bryanin.dev.questionnairebackend.office.security.SecurityRole;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

@Configuration
public class InitialConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            EmployeeRepository employeeRepository,
            ProjectRepository projectRepository,
            TaskRepository taskRepository,
            TaskCommentRepository taskCommentRepository,
            CompanyRepository companyRepository,
            CustomerRepository customerRepository,
            AddressRepository addressRepository,
            ProjectsFilesRepository projectsFilesRepository,
            ProjectsPartnersRepository projectsPartnersRepository
    ) {
        return args -> {

// -------  Add companies

            Company luis = new Company(
                    1L,
                    "7714333985",
                    "ЛУИС+",
                    "ООО «Луис+Офис»",
                    null
            );

            Company company02 = new Company(
                    2L,
                    "4587923709",
                    "Компания 02",
                    "ООО «Компания 02»",
                    null
            );

            Company company03 = new Company(
                    3L,
                    "1593697845",
                    "Компания 02",
                    "ООО «Компания 02»",
                    null
            );

            Company company04 = new Company(
                    4L,
                    "4859485926",
                    "Компания 04",
                    "ООО «Компания 04»",
                    null
            );

            Company company05 = new Company(
                    5L,
                    "5948562368",
                    "Компания 05",
                    "ООО «Компания 05»",
                    null
            );

            List<Company> companyList = new ArrayList<>(Arrays.asList(luis, company02, company03, company04, company05));

// -------  Add employees

            Employee employee01 = new Employee(
                    1L,
                    "kondrich.anastasiya@luis.ru",
                    "Анастасия",
                    "Кондрич",
                    luis.getId(),
                    "+7-987-831-63-55",
                    "$2a$12$FbzPqSUgdeeCazRorCOy1.Yeh8wOFhFU5sNsFPpT9H4YJxtLufdki",
                    EmployeePosition.HEAD_OF_ENGINEER_PROMOTION_DEPARTMENT,
                    SecurityRole.HEAD_OF_ENGINEER_PROMOTION_DEPARTMENT,
                    AccessStatus.ACTIVE,
                    null,
                    null
            );
            Employee employee02 = new Employee(
                    2L,
                    "bryanin.dmitriy@luis.ru",
                    "Дмитрий",
                    "Брянин",
                    luis.getId(),
                    "+7-965-115-40-55",
                    "$2a$12$FbzPqSUgdeeCazRorCOy1.Yeh8wOFhFU5sNsFPpT9H4YJxtLufdki",
                    EmployeePosition.MIDDLE_ENGINEER,
                    SecurityRole.MIDDLE_ENGINEER,
                    AccessStatus.ACTIVE,
                    null,
                    null
            );
            Employee employee03 = new Employee(
                    3L,
                    "perov.roman@luis.ru",
                    "Роман",
                    "Перов",
                    luis.getId(),
                    "+7-905-531-66-05",
                    "$2a$12$FbzPqSUgdeeCazRorCOy1.Yeh8wOFhFU5sNsFPpT9H4YJxtLufdki",
                    EmployeePosition.MIDDLE_ENGINEER,
                    SecurityRole.MIDDLE_ENGINEER,
                    AccessStatus.ACTIVE,
                    null,
                    null
            );
            Employee employee04 = new Employee(
                    4L,
                    "konovalov.sergey@luis.ru",
                    "Сергей",
                    "Коновалов",
                    luis.getId(),
                    "+7-905-105-75-50",
                    "$2a$12$FbzPqSUgdeeCazRorCOy1.Yeh8wOFhFU5sNsFPpT9H4YJxtLufdki",
                    EmployeePosition.MIDDLE_ENGINEER,
                    SecurityRole.MIDDLE_ENGINEER,
                    AccessStatus.ACTIVE,
                    null,
                    null
            );
            Employee employee05 = new Employee(
                    5L,
                    "romanov.boris@luis.ru",
                    "Борис",
                    "Романов",
                    luis.getId(),
                    "+7-927-623-77-01",
                    "$2a$12$FbzPqSUgdeeCazRorCOy1.Yeh8wOFhFU5sNsFPpT9H4YJxtLufdki",
                    EmployeePosition.HEAD_OF_DESIGN_DEPARTMENT,
                    SecurityRole.HEAD_OF_DESIGN_DEPARTMENT,
                    AccessStatus.ACTIVE,
                    null,
                    null
            );
            Employee employee06 = new Employee(
                    6L,
                    "novikov.vladimir@luis.ru",
                    "Владимир",
                    "Новиков",
                    luis.getId(),
                    "+7-987-311-17-98",
                    "$2a$12$FbzPqSUgdeeCazRorCOy1.Yeh8wOFhFU5sNsFPpT9H4YJxtLufdki",
                    EmployeePosition.SENIOR_DESIGNER,
                    SecurityRole.SENIOR_DESIGNER,
                    AccessStatus.ACTIVE,
                    null,
                    null
            );
            Employee employee07 = new Employee(
                    7L,
                    "zhuravlev.fedor@luis.ru",
                    "Федор",
                    "Журавлев",
                    luis.getId(),
                    "+7-906-153-18-45",
                    "$2a$12$FbzPqSUgdeeCazRorCOy1.Yeh8wOFhFU5sNsFPpT9H4YJxtLufdki",
                    EmployeePosition.SENIOR_DESIGNER,
                    SecurityRole.SENIOR_DESIGNER,
                    AccessStatus.ACTIVE,
                    null,
                    null
            );
            Employee employee08 = new Employee(
                    8L,
                    "marakulin.alexander@luis.ru",
                    "Александр",
                    "Маракулин",
                    luis.getId(),
                    "+7-986-986-07-11",
                    "$2a$12$FbzPqSUgdeeCazRorCOy1.Yeh8wOFhFU5sNsFPpT9H4YJxtLufdki",
                    EmployeePosition.MIDDLE_DESIGNER,
                    SecurityRole.MIDDLE_DESIGNER,
                    AccessStatus.ACTIVE,
                    null,
                    null
            );
            Employee employee09 = new Employee(
                    9L,
                    "zaporozhceva.irina@luis.ru",
                    "Ирина",
                    "Запорожцева",
                    luis.getId(),
                    "+7-927-152-21-33",
                    "$2a$12$FbzPqSUgdeeCazRorCOy1.Yeh8wOFhFU5sNsFPpT9H4YJxtLufdki",
                    EmployeePosition.MIDDLE_DESIGNER,
                    SecurityRole.MIDDLE_DESIGNER,
                    AccessStatus.ACTIVE,
                    null,
                    null
            );
            Employee employee10 = new Employee(
                    10L,
                    "chuhas.irina@luis.ru",
                    "Ирина",
                    "Чухась",
                    luis.getId(),
                    "+7-904-243-70-16",
                    "$2a$12$FbzPqSUgdeeCazRorCOy1.Yeh8wOFhFU5sNsFPpT9H4YJxtLufdki",
                    EmployeePosition.MIDDLE_DESIGNER,
                    SecurityRole.MIDDLE_DESIGNER,
                    AccessStatus.ACTIVE,
                    null,
                    null
            );
            Employee employee11 = new Employee(
                    11L,
                    "rassohin.mihail@luis.ru",
                    "Михаил",
                    "Рассохин",
                    luis.getId(),
                    "+7-929-779-28-28",
                    "$2a$12$FbzPqSUgdeeCazRorCOy1.Yeh8wOFhFU5sNsFPpT9H4YJxtLufdki",
                    EmployeePosition.MIDDLE_DESIGNER,
                    SecurityRole.MIDDLE_DESIGNER,
                    AccessStatus.ACTIVE,
                    null,null
            );
            List<Employee> employeeList = new ArrayList<>();
            employeeList.add(employee01);
            employeeList.add(employee02);
            employeeList.add(employee03);
            employeeList.add(employee04);
            employeeList.add(employee05);
            employeeList.add(employee06);
            employeeList.add(employee07);
            employeeList.add(employee08);
            employeeList.add(employee09);
            employeeList.add(employee10);
            employeeList.add(employee11);

// -------  Add customers

            Customer customer01 = new Customer(
                    1L,
                    "customer01@company02.com",
                    "customer01name",
                    "customer01surname",
                    2L,
                    "+79873692514",
                    "000",
                    CustomerPosition.DIRECTOR,
                    SecurityRole.CUSTOMER,
                    AccessStatus.ACTIVE,
                    null
            );

            Customer customer02 = new Customer(
                    2L,
                    "customer02@company02.com",
                    "customer02name",
                    "customer02surname",
                    2L,
                    "+79873692515",
                    "000",
                    CustomerPosition.DESIGNER,
                    SecurityRole.CUSTOMER,
                    AccessStatus.ACTIVE,
                    Arrays.asList(company04, company05)
            );

            Customer customer03 = new Customer(
                    3L,
                    "customer03@company03.com",
                    "customer03name",
                    "customer03surname",
                    3L,
                    "+79636932514",
                    "000",
                    CustomerPosition.DESIGNER,
                    SecurityRole.CUSTOMER,
                    AccessStatus.ACTIVE,
                    null
            );

            Customer customer04 = new Customer(
                    4L,
                    "customer04@company04.com",
                    "customer04name",
                    "customer04surname",
                    4L,
                    "+79771592634",
                    "000",
                    CustomerPosition.DIRECTOR,
                    SecurityRole.CUSTOMER,
                    AccessStatus.ACTIVE,
                    null
            );

            Customer customer05 = new Customer(
                    5L,
                    "customer05@company04.com",
                    "customer05name",
                    "customer05surname",
                    4L,
                    "+79771592677",
                    "000",
                    CustomerPosition.HEAD_OF_DEPARTMENT,
                    SecurityRole.CUSTOMER,
                    AccessStatus.ACTIVE,
                    null
            );

            List<Customer> customerList = new ArrayList<>(Arrays.asList(customer01, customer02, customer03, customer04, customer05));

// -------  Add projects

            Address address01 = new Address(
                    1L,
                    125040,
                    "Россия",
                    "Московская",
                    "Москва",
                    "",
                    "1-я Ямского Поля",
                    "28",
                    "");


            Project project01 = new Project(
                    1L,
                    "1C_000001",
                    "ТЦ Химки",
                    "Строительство торгового центра в Химках",
                    "bryanin.dmitriy@luis.ru",
                    address01,
                    LocalDate.of(2022, 2, 11),
                    Project.Status.ARCHIVED,
                    null,
                    null,
                    null
            );
            Project project02 = new Project(
                    2L,
                    "1C_000002",
                    "Больница в ст. Медведевская",
                    "Больница на 60 мест. Реализация в 2023 году",
                    "konovalov.sergey@luis.ru",
                    address01,
                    LocalDate.of(2022, 3, 20),
                    Project.Status.ARCHIVED,
                    null,
                    null,
                    null
            );
            Project project03 = new Project(
                    3L,
                    "1C_000003",
                    "Гостиница Hot Season",
                    "Гостиница 5 звезд в Москве на Можайском шоссе",
                    "perov.roman@luis.ru",
                    address01,
                    LocalDate.of(2022, 4, 1),
                    Project.Status.ARCHIVED,
                    null,
                    null,
                    null
            );
            Project project04 = new Project(
                    4L,
                    "1C_000004",
                    "Кинотеатр Родина",
                    "Сеть кинотеатров на юге России",
                    "perov.roman@luis.ru",
                    address01,
                    LocalDate.of(2022, 4, 2),
                    Project.Status.ARCHIVED,
                    null,
                    null,
                    null
            );
            Project project05 = new Project(
                    5L,
                    "1C_000005",
                    "Музей музыки 19 века",
                    "Объект культурного наследия. Реставрация. Министерство Культуры",
                    "konovalov.sergey@luis.ru",
                    address01,
                    LocalDate.of(2022, 2, 23),
                    Project.Status.ARCHIVED,
                    null,
                    null,
                    null
            );
            Project project06 = new Project(
                    6L,
                    "1C_000006",
                    "Средняя общеобразовательная школа на 860 мест в г. Люберцы",
                    "Часть комплексной застройки ГК Инград",
                    "bryanin.dmitriy@luis.ru",
                    address01,
                    LocalDate.of(2022, 4, 21),
                    Project.Status.ARCHIVED,
                    null,
                    null,
                    null
            );
            Project project07 = new Project(
                    7L,
                    "1C_000007",
                    "Политехнический институт в г. Иваново",
                    "Объединенный с Педагогическим институтом в 2012 году (ул. К.Маркса)",
                    "bryanin.dmitriy@luis.ru",
                    address01,
                    LocalDate.of(2021, 12, 16),
                    Project.Status.ARCHIVED,
                    null,
                    null,
                    null
            );

            List<Project> projectList = new ArrayList<>();
            projectList.add(project01);
            projectList.add(project02);
            projectList.add(project03);
            projectList.add(project04);
            projectList.add(project05);
            projectList.add(project06);
            projectList.add(project07);

            ProjectsPartners projectsPartners01 = new ProjectsPartners(
                    1L,
                    company02.getId(),
                    company02.getTitleShort(),
                    project01.getId(),
                    ProjectsPartners.PartnerRole.DESIGNER
            );

            ProjectsPartners projectsPartners02 = new ProjectsPartners(
                    2L,
                    company03.getId(),
                    company03.getTitleShort(),
                    project01.getId(),
                    ProjectsPartners.PartnerRole.END_USER
            );

            ProjectsPartners projectsPartners03 = new ProjectsPartners(
                    3L,
                    company04.getId(),
                    company04.getTitleShort(),
                    project01.getId(),
                    ProjectsPartners.PartnerRole.GENERAL_CONTRACTOR
            );

            project01.setProjectsPartners(Arrays.asList(projectsPartners01, projectsPartners02, projectsPartners03));

            ProjectsPartners projectsPartners04 = new ProjectsPartners(
                    4L,
                    company03.getId(),
                    company03.getTitleShort(),
                    project04.getId(),
                    ProjectsPartners.PartnerRole.DESIGNER
            );

            ProjectsPartners projectsPartners05 = new ProjectsPartners(
                    5L,
                    company04.getId(),
                    company04.getTitleShort(),
                    project04.getId(),
                    ProjectsPartners.PartnerRole.END_USER
            );

            ProjectsPartners projectsPartners06 = new ProjectsPartners(
                    6L,
                    company05.getId(),
                    company05.getTitleShort(),
                    project04.getId(),
                    ProjectsPartners.PartnerRole.GENERAL_CONTRACTOR
            );

            project04.setProjectsPartners(Arrays.asList(projectsPartners04, projectsPartners05, projectsPartners06));

// -------  Add tasks

            String appzPath = "src/main/resources/questionnaires/APPZ.json";
            String questionnaire01 = new String(Files.readAllBytes(Paths.get(appzPath)), StandardCharsets.UTF_8);
            String auptPath = "src/main/resources/questionnaires/AUPT.json";
            String questionnaire02 = new String(Files.readAllBytes(Paths.get(auptPath)), StandardCharsets.UTF_8);
            String skudPath = "src/main/resources/questionnaires/SKUD.json";
            String questionnaire03 = new String(Files.readAllBytes(Paths.get(skudPath)), StandardCharsets.UTF_8);
            String sotPath = "src/main/resources/questionnaires/SOT.json";
            String questionnaire04 = new String(Files.readAllBytes(Paths.get(sotPath)), StandardCharsets.UTF_8);
            String sotsPath = "src/main/resources/questionnaires/SOTS.json";
            String questionnaire05 = new String(Files.readAllBytes(Paths.get(sotsPath)), StandardCharsets.UTF_8);
            String souePath = "src/main/resources/questionnaires/SOUE.json";
            String questionnaire06 = new String(Files.readAllBytes(Paths.get(souePath)), StandardCharsets.UTF_8);
            String spsPath = "src/main/resources/questionnaires/SPS.json";
            String questionnaire07 = new String(Files.readAllBytes(Paths.get(spsPath)), StandardCharsets.UTF_8);

            Task task01 = new Task(
                    1L,
                    "bryanin.dmitriy@luis.ru",
                    Status.NEW,
                    Complexity.LEVEL_1,
                    1L,
                    null,
                    SecuritySystem.APPZ,
                    questionnaire01,
                    null,
                    new HashSet<Stage>(Arrays.asList(Stage.SPECIFICATION, Stage.ARRANGEMENT_OF_EQUIPMENT)),
                    LocalDate.of(2022, 1, 30),
                    LocalDate.of(2022, 2, 27),
                    LocalDate.of(2022, 2, 15),
                    LocalDate.of(2022, 2, 05),
                    LocalDate.of(2022, 2, 15),
                    LocalDate.of(2022, 2, 16),
                    null);

            project01.setStatus(Project.Status.ACTIVE);

            Task task02 = new Task(
                   2L,
                    "perov.roman@luis.ru",
                    Status.NEW,
                    Complexity.LEVEL_1,
                    5L,
                    null,
                    SecuritySystem.AUPT,
                    questionnaire02,
                    null,
                    new HashSet<Stage>(Arrays.asList(Stage.SPECIFICATION)),
                    LocalDate.of(2022, 2, 13),
                    LocalDate.of(2022, 2, 27),
                    LocalDate.of(2022, 2, 15),
                    LocalDate.of(2022, 2, 05),
                    LocalDate.of(2022, 2, 15),
                    LocalDate.of(2022, 2, 16),
                    null);

            project05.setStatus(Project.Status.ACTIVE);

            Task task03 = new Task(
                    3L,
                    "konovalov.sergey@luis.ru",
                    Status.NEW,
                    Complexity.LEVEL_1,
                    3L,
                    null,
                    SecuritySystem.SKUD,
                    questionnaire03,
                    null,
                    new HashSet<Stage>(Arrays.asList(Stage.STRUCTURAL_SCHEME)),
                    LocalDate.of(2022, 3, 05),
                    LocalDate.of(2022, 3, 27),
                    LocalDate.of(2022, 3, 15),
                    LocalDate.of(2022, 3, 05),
                    LocalDate.of(2022, 3, 15),
                    LocalDate.of(2022, 3, 16),
                    null);

            project03.setStatus(Project.Status.ACTIVE);

            Task task04 = new Task(
                    4L,
                    "bryanin.dmitriy@luis.ru",
                    Status.NEW,
                    Complexity.LEVEL_1,
                    1L,
                    null,
                    SecuritySystem.SOT,
                    questionnaire04,
                    null,
                    new HashSet<Stage>(Arrays.asList(Stage.SPECIFICATION, Stage.ARRANGEMENT_OF_EQUIPMENT)),
                    LocalDate.of(2022, 1, 30),
                    LocalDate.of(2022, 2, 27),
                    LocalDate.of(2022, 2, 15),
                    LocalDate.of(2022, 2, 05),
                    LocalDate.of(2022, 2, 15),
                    LocalDate.of(2022, 2, 16),
                    null);

            project01.setStatus(Project.Status.ACTIVE);

            Task task05 = new Task(
                    5L,
                    "perov.roman@luis.ru",
                    Status.NEW,
                    Complexity.LEVEL_1,
                    4L,
                    null,
                    SecuritySystem.SOTS,
                    questionnaire05,
                    null,
                    new HashSet<Stage>(Arrays.asList(Stage.SPECIFICATION, Stage.ARRANGEMENT_OF_EQUIPMENT)),
                    LocalDate.of(2022, 2, 16),
                    LocalDate.of(2022, 2, 27),
                    LocalDate.of(2022, 2, 20),
                    LocalDate.of(2022, 2, 18),
                    LocalDate.of(2022, 2, 20),
                    LocalDate.of(2022, 2, 27),
                    null);

            project04.setStatus(Project.Status.ACTIVE);

            Task task06 = new Task(
                    6L,
                    "konovalov.sergey@luis.ru",
                    Status.NEW,
                    Complexity.LEVEL_1,
                    4L,
                    null,
                    SecuritySystem.SOUE,
                    questionnaire06,
                    null,
                    new HashSet<Stage>(Arrays.asList(Stage.SPECIFICATION, Stage.ARRANGEMENT_OF_EQUIPMENT, Stage.ACOUSTIC_CALCULATION)),
                    LocalDate.of(2022, 2, 10),
                    LocalDate.of(2022, 2, 27),
                    LocalDate.of(2022, 2, 15),
                    LocalDate.of(2022, 2, 11),
                    LocalDate.of(2022, 2, 15),
                    LocalDate.of(2022, 2, 16),
                    null);

            project04.setStatus(Project.Status.ACTIVE);

            Task task07 = new Task(
                    7L,
                    "bryanin.dmitriy@luis.ru",
                    Status.NEW,
                    Complexity.LEVEL_1,
                    1L,
                    null,
                    SecuritySystem.SPS,
                    questionnaire07,
                    null,
                    new HashSet<Stage>(Arrays.asList(Stage.SPECIFICATION, Stage.ARRANGEMENT_OF_EQUIPMENT)),
                    LocalDate.of(2022, 12, 30),
                    LocalDate.of(2023, 2, 27),
                    LocalDate.of(2023, 2, 15),
                    LocalDate.of(2023, 2, 05),
                    LocalDate.of(2023, 2, 15),
                    LocalDate.of(2023, 2, 16),
                    null);

            project01.setStatus(Project.Status.ACTIVE);

            List<Task> taskList = new ArrayList<>();
            taskList.add(task01);
            taskList.add(task02);
            taskList.add(task03);
            taskList.add(task04);
            taskList.add(task05);
            taskList.add(task06);
            taskList.add(task07);

            ProjectsFiles projectsFiles01 = new ProjectsFiles(
                    1L,
                    project01.getId(),
                    task01.getId(),
                    "https://disk.yandex.ru/d/2ZzId3Qyzf9agA"
            );

// -------  Add comments

            Comment taskComment01 = new Comment(
                    1L,
                    "bryanin.dmitriy@luis.ru",
                    1L,
                    LocalDate.of(2022, 5, 10),
                    "Инициализация заявки",
                    ""
            );
            Comment taskComment02 = new Comment(
                    2L,
                    "romanov.boris@luis.ru",
                    1L,
                    LocalDate.of(2022, 5, 10),
                    "Не приложены исходные данные",
                    ""
            );
            Comment taskComment03 = new Comment(
                    3L,
                    "bryanin.dmitriy@luis.ru",
                    1L,
                    LocalDate.of(2022, 5, 11),
                    "Добавил планировки",
                    "https://disk.yandex.ru/d/2ZzId3Qyzf9agA"
            );
            Comment taskComment04 = new Comment(
                    4L,
                    "romanov.boris@luis.ru",
                    1L,
                    LocalDate.of(2022, 5, 12),
                    "Принято",
                    ""
            );
            List<Comment> taskCommentList = new ArrayList<>();
            taskCommentList.add(taskComment01);
            taskCommentList.add(taskComment02);
            taskCommentList.add(taskComment03);
            taskCommentList.add(taskComment04);

            companyRepository.saveAll(companyList);
            employeeRepository.saveAll(employeeList);
            customerRepository.saveAll(customerList);
            addressRepository.save(address01);
            taskRepository.saveAll(taskList);
            taskCommentRepository.saveAll(taskCommentList);
            projectRepository.saveAll(projectList);
            projectsPartnersRepository.saveAll(Arrays.asList(projectsPartners01, projectsPartners02, projectsPartners03, projectsPartners04, projectsPartners05, projectsPartners06));
            projectsFilesRepository.saveAll(Arrays.asList(projectsFiles01));

        };
    }
}
