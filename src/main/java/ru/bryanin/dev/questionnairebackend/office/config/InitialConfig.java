package ru.bryanin.dev.questionnairebackend.office.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.bryanin.dev.questionnairebackend.office.model.project.Project;
import ru.bryanin.dev.questionnairebackend.office.model.task.*;
import ru.bryanin.dev.questionnairebackend.office.model.task.System;
import ru.bryanin.dev.questionnairebackend.office.model.user.BasicUser;
import ru.bryanin.dev.questionnairebackend.office.model.user.Position;
import ru.bryanin.dev.questionnairebackend.office.repository.BasicUserRepository;
import ru.bryanin.dev.questionnairebackend.office.repository.ProjectRepository;
import ru.bryanin.dev.questionnairebackend.office.repository.TaskCommentRepository;
import ru.bryanin.dev.questionnairebackend.office.repository.TaskRepository;
import ru.bryanin.dev.questionnairebackend.office.security.SecurityRole;
import ru.bryanin.dev.questionnairebackend.office.security.AccessStatus;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Configuration
public class InitialConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            BasicUserRepository basicUserRepository,
            ProjectRepository projectRepository,
            TaskRepository taskRepository,
            TaskCommentRepository taskCommentRepository
            //QuestionnaireRepository questionnaireRepository
    ) {
        return args -> {

// -------  Add users

            BasicUser basicUser01 = new BasicUser(
                    1L,
                    "kondrich.anastasiya@luis.ru",
                    "Анастасия",
                    "Кондрич",
                    "LUIS+",
                    "+7-987-831-63-55",
                    "$2a$12$FbzPqSUgdeeCazRorCOy1.Yeh8wOFhFU5sNsFPpT9H4YJxtLufdki",
                    Position.HEAD_OF_ENGINEER_PROMOTION_DEPARTMENT,
                    SecurityRole.HEAD_OF_ENGINEER_PROMOTION_DEPARTMENT,
                    AccessStatus.ACTIVE
            );
            BasicUser basicUser02 = new BasicUser(
                    2L,
                    "bryanin.dmitriy@luis.ru",
                    "Дмитрий",
                    "Брянин",
                    "LUIS+",
                    "+7-965-115-40-55",
                    "$2a$12$FbzPqSUgdeeCazRorCOy1.Yeh8wOFhFU5sNsFPpT9H4YJxtLufdki",
                    Position.MIDDLE_ENGINEER,
                    SecurityRole.MIDDLE_ENGINEER,
                    AccessStatus.ACTIVE
            );
            BasicUser basicUser03 = new BasicUser(
                    3L,
                    "perov.roman@luis.ru",
                    "Роман",
                    "Перов",
                    "LUIS+",
                    "+7-905-531-66-05",
                    "$2a$12$FbzPqSUgdeeCazRorCOy1.Yeh8wOFhFU5sNsFPpT9H4YJxtLufdki",
                    Position.MIDDLE_ENGINEER,
                    SecurityRole.MIDDLE_ENGINEER,
                    AccessStatus.ACTIVE
            );
            BasicUser basicUser04 = new BasicUser(
                    4L,
                    "konovalov.sergey@luis.ru",
                    "Сергей",
                    "Коновалов",
                    "LUIS+",
                    "+7-905-105-75-50",
                    "$2a$12$FbzPqSUgdeeCazRorCOy1.Yeh8wOFhFU5sNsFPpT9H4YJxtLufdki",
                    Position.MIDDLE_ENGINEER,
                    SecurityRole.MIDDLE_ENGINEER,
                    AccessStatus.ACTIVE
            );
            BasicUser basicUser05 = new BasicUser(
                    5L,
                    "romanov.boris@luis.ru",
                    "Борис",
                    "Романов",
                    "LUIS+",
                    "+7-927-623-77-01",
                    "$2a$12$FbzPqSUgdeeCazRorCOy1.Yeh8wOFhFU5sNsFPpT9H4YJxtLufdki",
                    Position.HEAD_OF_DESIGN_DEPARTMENT,
                    SecurityRole.HEAD_OF_DESIGN_DEPARTMENT,
                    AccessStatus.ACTIVE
            );
            BasicUser basicUser06 = new BasicUser(
                    6L,
                    "novikov.vladimir@luis.ru",
                    "Владимир",
                    "Новиков",
                    "LUIS+",
                    "+7-987-311-17-98",
                    "$2a$12$FbzPqSUgdeeCazRorCOy1.Yeh8wOFhFU5sNsFPpT9H4YJxtLufdki",
                    Position.SENIOR_DESIGNER,
                    SecurityRole.SENIOR_DESIGNER,
                    AccessStatus.ACTIVE
            );
            BasicUser basicUser07 = new BasicUser(
                    7L,
                    "zhuravlev.fedor@luis.ru",
                    "Федор",
                    "Журавлев",
                    "LUIS+",
                    "+7-906-153-18-45",
                    "$2a$12$FbzPqSUgdeeCazRorCOy1.Yeh8wOFhFU5sNsFPpT9H4YJxtLufdki",
                    Position.SENIOR_DESIGNER,
                    SecurityRole.SENIOR_DESIGNER,
                    AccessStatus.ACTIVE
            );
            BasicUser basicUser08 = new BasicUser(
                    8L,
                    "marakulin.alexander@luis.ru",
                    "Александр",
                    "Маракулин",
                    "LUIS+",
                    "+7-986-986-07-11",
                    "$2a$12$FbzPqSUgdeeCazRorCOy1.Yeh8wOFhFU5sNsFPpT9H4YJxtLufdki",
                    Position.MIDDLE_DESIGNER,
                    SecurityRole.MIDDLE_DESIGNER,
                    AccessStatus.ACTIVE
            );
            BasicUser basicUser09 = new BasicUser(
                    9L,
                    "zaporozhceva.irina@luis.ru",
                    "Ирина",
                    "Запорожцева",
                    "LUIS+",
                    "+7-927-152-21-33",
                    "$2a$12$FbzPqSUgdeeCazRorCOy1.Yeh8wOFhFU5sNsFPpT9H4YJxtLufdki",
                    Position.MIDDLE_DESIGNER,
                    SecurityRole.MIDDLE_DESIGNER,
                    AccessStatus.ACTIVE
            );
            BasicUser basicUser10 = new BasicUser(
                    10L,
                    "chuhas.irina@luis.ru",
                    "Ирина",
                    "Чухась",
                    "LUIS+",
                    "+7-904-243-70-16",
                    "$2a$12$FbzPqSUgdeeCazRorCOy1.Yeh8wOFhFU5sNsFPpT9H4YJxtLufdki",
                    Position.MIDDLE_DESIGNER,
                    SecurityRole.MIDDLE_DESIGNER,
                    AccessStatus.ACTIVE
            );
            BasicUser basicUser11 = new BasicUser(
                    11L,
                    "rassohin.mihail@luis.ru",
                    "Михаил",
                    "Рассохин",
                    "LUIS+",
                    "+7-929-779-28-28",
                    "$2a$12$FbzPqSUgdeeCazRorCOy1.Yeh8wOFhFU5sNsFPpT9H4YJxtLufdki",
                    Position.MIDDLE_DESIGNER,
                    SecurityRole.MIDDLE_DESIGNER,
                    AccessStatus.ACTIVE
            );
            List<BasicUser> basicUserList = new ArrayList<>();
            basicUserList.add(basicUser01);
            basicUserList.add(basicUser02);
            basicUserList.add(basicUser03);
            basicUserList.add(basicUser04);
            basicUserList.add(basicUser05);
            basicUserList.add(basicUser06);
            basicUserList.add(basicUser07);
            basicUserList.add(basicUser08);
            basicUserList.add(basicUser09);
            basicUserList.add(basicUser10);
            basicUserList.add(basicUser11);
            basicUserRepository.saveAll(basicUserList);

// -------  Add projects

            Project project01 = new Project(
                    1L,
                    "1C_000001",
                    "ТЦ Химки",
                    "Строительство торгового центра в Химках",
                    "bryanin.dmitriy@luis.ru",
                    "Московская область",
                    LocalDate.of(2022, 2, 11)
            );
            Project project02 = new Project(
                    2L,
                    "1C_000002",
                    "Больница в ст. Медведевская",
                    "Больница на 60 мест. Реализация в 2023 году",
                    "konovalov.sergey@luis.ru",
                    "Краснодарский край",
                    LocalDate.of(2022, 3, 20)
            );
            Project project03 = new Project(
                    3L,
                    "1C_000003",
                    "Гостиница Hot Season",
                    "Гостиница 5 звезд в Москве на Можайском шоссе",
                    "perov.roman@luis.ru",
                    "Московская область",
                    LocalDate.of(2022, 4, 1)
            );
            Project project04 = new Project(
                    4L,
                    "1C_000004",
                    "Кинотеатр Родина",
                    "Сеть кинотеатров на юге России",
                    "perov.roman@luis.ru",
                    "Ростовская область",
                    LocalDate.of(2022, 4, 2)
            );
            Project project05 = new Project(
                    5L,
                    "1C_000005",
                    "Музей музыки 19 века",
                    "Объект культурного наследия. Реставрация. Министерство Культуры",
                    "konovalov.sergey@luis.ru",
                    "Калужская область",
                    LocalDate.of(2022, 2, 23)
            );
            Project project06 = new Project(
                    6L,
                    "1C_000006",
                    "Средняя общеобразовательная школа на 860 мест в г. Люберцы",
                    "Часть комплексной застройки ГК Инград",
                    "bryanin.dmitriy@luis.ru",
                    "Московская область",
                    LocalDate.of(2022, 4, 21)
            );
            Project project07 = new Project(
                    7L,
                    "1C_000007",
                    "Политехнический институт в г. Иваново",
                    "Объединенный с Педагогическим институтом в 2012 году (ул. К.Маркса)",
                    "bryanin.dmitriy@luis.ru",
                    "Московская область",
                    LocalDate.of(2021, 12, 16)
            );
            List<Project> projectList = new ArrayList<>();
            projectList.add(project01);
            projectList.add(project02);
            projectList.add(project03);
            projectList.add(project04);
            projectList.add(project05);
            projectList.add(project06);
            projectList.add(project07);
            projectRepository.saveAll(projectList);

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
                    Status.IN_HEAP,
                    Complexity.LEVEL_1,
                    1L,
                    null,
                    System.APPZ,
                    questionnaire01,
                    null,
                    LocalDate.of(2022, 1, 30));

           Task task02 = new Task(
                   2L,
                    "perov.roman@luis.ru",
                    Status.IN_HEAP,
                    Complexity.LEVEL_1,
                    5L,
                    null,
                    System.AUPT,
                    questionnaire02,
                    null,
                    LocalDate.of(2022, 2, 13));

            Task task03 = new Task(
                    3L,
                    "konovalov.sergey@luis.ru",
                    Status.IN_HEAP,
                    Complexity.LEVEL_1,
                    3L,
                    null,
                    System.SKUD,
                    questionnaire03,
                    null,
                    LocalDate.of(2022, 3, 05));

            Task task04 = new Task(
                    4L,
                    "bryanin.dmitriy@luis.ru",
                    Status.IN_HEAP,
                    Complexity.LEVEL_1,
                    1L,
                    null,
                    System.SOT,
                    questionnaire04,
                    null,
                    LocalDate.of(2022, 1, 30));

            Task task05 = new Task(
                    5L,
                    "perov.roman@luis.ru",
                    Status.IN_HEAP,
                    Complexity.LEVEL_1,
                    4L,
                    null,
                    System.SOTS,
                    questionnaire05,
                    null,
                    LocalDate.of(2022, 2, 16));

            Task task06 = new Task(
                    6L,
                    "konovalov.sergey@luis.ru",
                    Status.IN_HEAP,
                    Complexity.LEVEL_1,
                    4L,
                    null,
                    System.SOUE,
                    questionnaire06,
                    null,
                    LocalDate.of(2022, 2, 10));

            Task task07 = new Task(
                    7L,
                    "bryanin.dmitriy@luis.ru",
                    Status.IN_HEAP,
                    Complexity.LEVEL_1,
                    1L,
                    null,
                    System.SPS,
                    questionnaire07,
                    null,
                    LocalDate.of(2022, 12, 30));

            List<Task> taskList = new ArrayList<>();
            taskList.add(task01);
            taskList.add(task02);
            taskList.add(task03);
            taskList.add(task04);
            taskList.add(task05);
            taskList.add(task06);
            taskList.add(task07);
            taskRepository.saveAll(taskList);

// -------  Add comments
            Comment taskComment01 = new Comment(
                    1L,
                    2L,
                    1L,
                    LocalDate.of(2022, 5, 10),
                    "Инициализация заявки"
            );
            Comment taskComment02 = new Comment(
                    2L,
                    2L,
                    1L,
                    LocalDate.of(2022, 5, 10),
                    "Обновление информации"
            );
            Comment taskComment03 = new Comment(
                    3L,
                    4L,
                    1L,
                    LocalDate.of(2022, 5, 11),
                    "Вопрос по заявке"
            );
            Comment taskComment04 = new Comment(
                    4L,
                    2L,
                    1L,
                    LocalDate.of(2022, 5, 12),
                    "Ответ по заявке"
            );
            List<Comment> taskCommentList = new ArrayList<>();
            taskCommentList.add(taskComment01);
            taskCommentList.add(taskComment02);
            taskCommentList.add(taskComment03);
            taskCommentList.add(taskComment04);
            taskCommentRepository.saveAll(taskCommentList);

        };
    }
}
