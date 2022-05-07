package ru.bryanin.dev.questionnairebackend.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.bryanin.dev.questionnairebackend.model.BasicUser;
import ru.bryanin.dev.questionnairebackend.model.Position;
import ru.bryanin.dev.questionnairebackend.model.security.SecurityRole;
import ru.bryanin.dev.questionnairebackend.model.security.AccessStatus;
import ru.bryanin.dev.questionnairebackend.repository.BasicUserRepository;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner commandLineRunner(BasicUserRepository basicUserRepository) {
        return args -> {
            BasicUser basicUser01 = new BasicUser(
                    1L,
                    "kondrich.anastasiya@luis.ru",
                    "Anastasiya",
                    "Kondrich",
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
                    "Dmitriy",
                    "Bryanin",
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
                    "Roman",
                    "Perov",
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
                    "Sergey",
                    "Konovalov",
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
                    "Boris",
                    "Romanov",
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
                    "Vladimir",
                    "Novikov",
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
                    "Fedor",
                    "Zhuravlev",
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
                    "Alexandr",
                    "Marakulin",
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
                    "Irina",
                    "Zaporozhceva",
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
                    "Irina",
                    "Chuhas",
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
                    "Mihail",
                    "Rassohin",
                    "LUIS+",
                    "+7-929-779-28-28",
                    "$2a$12$FbzPqSUgdeeCazRorCOy1.Yeh8wOFhFU5sNsFPpT9H4YJxtLufdki",
                    Position.MIDDLE_DESIGNER,
                    SecurityRole.MIDDLE_DESIGNER,
                    AccessStatus.ACTIVE
            );
            BasicUser basicUser12 = new BasicUser(
                    12L,
                    "veretennikov.sergey@luis.ru",
                    "Sergey",
                    "Veretennikov",
                    "LUIS+",
                    "+7-927-059-40-72",
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
            basicUserList.add(basicUser12);
            basicUserRepository.saveAll(basicUserList);
        };
    }
}
