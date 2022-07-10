package ru.bryanin.dev.questionnairebackend.config;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.bryanin.dev.questionnairebackend.model.project.Project;
import ru.bryanin.dev.questionnairebackend.model.questionnaire.Questionnaire;
import ru.bryanin.dev.questionnairebackend.model.task.*;
import ru.bryanin.dev.questionnairebackend.model.user.BasicUser;
import ru.bryanin.dev.questionnairebackend.model.user.Position;
import ru.bryanin.dev.questionnairebackend.repository.*;
import ru.bryanin.dev.questionnairebackend.security.SecurityRole;
import ru.bryanin.dev.questionnairebackend.security.AccessStatus;

import java.lang.System;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

// ------- Add Questionnaires
//
//            String jsonString01 = "{\n" +
//                    "    \"System\" : \"SOT\",\n" +
//                    "    \"Content\": [\n" +
//                    "        {\n" +
//                    "            \"question\": \"Какой тип системы ВН требуется организовать на объекте?\",\n" +
//                    "            \"answer\": {\n" +
//                    "                \"simpleAnswer\": \"Цифровой или аналоговый\",\n" +
//                    "                \"multipleAnswer\": []\n" +
//                    "            }\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "            \"question\": \"Существует ли минимально допустимое разрешение (МП)?\",\n" +
//                    "            \"answer\": {\n" +
//                    "                \"simpleAnswer\": \"Нет\",\n" +
//                    "                \"multipleAnswer\": []\n" +
//                    "            }\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "            \"question\": \"Камеры для внутреннего наблюдения установить в помещениях:\",\n" +
//                    "            \"answer\": {\n" +
//                    "                \"simpleAnswer\": \"Холлы, коридоры, входы на этаж и в здание\",\n" +
//                    "                \"multipleAnswer\": []\n" +
//                    "            }\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "            \"question\": \"Укажите особый температурный режим в местах установки камер (например, отметьте, какие помещения неотапливаемые) /дополнительные требования к оборудованию (например, взрывозащищенное оборудование)\",\n" +
//                    "            \"answer\": {\n" +
//                    "                \"simpleAnswer\": \"Нет\",\n" +
//                    "                \"multipleAnswer\": []\n" +
//                    "            }\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "            \"question\": \"Способ прокладки кабельных линий к камерам в помещениях\",\n" +
//                    "            \"answer\": {\n" +
//                    "                \"simpleAnswer\": \"В гофре и кабель-канале\",\n" +
//                    "                \"multipleAnswer\": []\n" +
//                    "            }\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "            \"question\": \"Камеры для наружного наблюдения установить для наблюдения за объектами\",\n" +
//                    "            \"answer\": {\n" +
//                    "                \"simpleAnswer\": \"По периметру здания и на въезде на территорию\",\n" +
//                    "                \"multipleAnswer\": []\n" +
//                    "            }\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "            \"question\": \"Способ прокладки кабельных линий к уличным камерам\",\n" +
//                    "            \"answer\": {\n" +
//                    "                \"simpleAnswer\": \"Воздушные линии (с применением троса), крепление к фасаду и столбам\",\n" +
//                    "                \"multipleAnswer\": []\n" +
//                    "            }\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "            \"question\": \"Укажите, какие задачи системы видеонаблюдения должны выполняться\",\n" +
//                    "            \"answer\": {\n" +
//                    "                \"simpleAnswer\": \"\",\n" +
//                    "                \"multipleAnswer\": [\n" +
//                    "                    {\n" +
//                    "                        \"question\": \"Обнаружение - позволяет определить с высокой степенью достоверности присутствие того или иного объекта в поле зрения камеры наблюдения\",\n" +
//                    "                        \"answer\": \"По периметру здания\"\n" +
//                    "                    },\n" +
//                    "                    {\n" +
//                    "                        \"question\": \"Распознавание - позволяет с высокой степенью уверенности заявить является ли текущий случай единичным или он уже был замечен ранее (тот же объект или другой)\",\n" +
//                    "                        \"answer\": \"Остальные помещения\"\n" +
//                    "                    },\n" +
//                    "                    {\n" +
//                    "                        \"question\": \"Идентификация - детали должны быть достаточно различимы для идентификации личности человека вне всякого сомнения\",\n" +
//                    "                        \"answer\": \"На входах в здание\"\n" +
//                    "                    }\n" +
//                    "                ]\n" +
//                    "            }\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "            \"question\": \"Необходимы ли функции видеоаналитики\",\n" +
//                    "            \"answer\": {\n" +
//                    "                \"simpleAnswer\": \"\",\n" +
//                    "                \"multipleAnswer\": [\n" +
//                    "                    {\n" +
//                    "                        \"question\": \"Распознавание лиц (если требуется, укажите количество каналов и объем базы лиц)\",\n" +
//                    "                        \"answer\": \"Нет\"\n" +
//                    "                    },\n" +
//                    "                    {\n" +
//                    "                        \"question\": \"Распознавание государственных регистрационных знаков транспортных средств (если требуется, укажите количество каналов и скорость движения транспортного средства)\",\n" +
//                    "                        \"answer\": \"Нет\"\n" +
//                    "                    },\n" +
//                    "                    {\n" +
//                    "                        \"question\": \"Распознавание марки и модели транспортного средства\",\n" +
//                    "                        \"answer\": \"Нет\"\n" +
//                    "                    },\n" +
//                    "                    {\n" +
//                    "                        \"question\": \"Детектор нарушений ПДД (если требуется, укажите количество каналов)\",\n" +
//                    "                        \"answer\": \"Нет\"\n" +
//                    "                    },\n" +
//                    "                    {\n" +
//                    "                        \"question\": \"Детектор ношения маски (если требуется, укажите количество каналов)\",\n" +
//                    "                        \"answer\": \"Нет\"\n" +
//                    "                    },\n" +
//                    "                    {\n" +
//                    "                        \"question\": \"Детектор дыма и огня (если требуется, укажите количество каналов)\",\n" +
//                    "                        \"answer\": \"Нет\"\n" +
//                    "                    },\n" +
//                    "                    {\n" +
//                    "                        \"question\": \"Трекер объектов (если требуется, укажите количество каналов)\",\n" +
//                    "                        \"answer\": \"Нет\"\n" +
//                    "                    },\n" +
//                    "                    {\n" +
//                    "                        \"question\": \"Нейротрекер объектов (если требуется, укажите количество каналов)\",\n" +
//                    "                        \"answer\": \"Нет\"\n" +
//                    "                    },\n" +
//                    "                    {\n" +
//                    "                        \"question\": \"Подключение POS-терминала (если требуется, укажите количество каналов)\",\n" +
//                    "                        \"answer\": \"Нет\"\n" +
//                    "                    },\n" +
//                    "                    {\n" +
//                    "                        \"question\": \"Централизованные веб-отчеты\",\n" +
//                    "                        \"answer\": \"Нет\"\n" +
//                    "                    },\n" +
//                    "                    {\n" +
//                    "                        \"question\": \"Интеграция со СКУД (если требуется, укажите бренд СКУД, количество контроллеров и пр. информацию)\",\n" +
//                    "                        \"answer\": \"Нет\"\n" +
//                    "                    },\n" +
//                    "                    {\n" +
//                    "                        \"question\": \"Интеграция систем периметральной охраны (если требуется, укажите бренд ПСО, количество контроллеров и пр. информацию)\",\n" +
//                    "                        \"answer\": \"Нет\"\n" +
//                    "                    },\n" +
//                    "                    {\n" +
//                    "                        \"question\": \"Интеграция с ЕЦХД (если требуется, укажите количество каналов)\",\n" +
//                    "                        \"answer\": \"Нет\"\n" +
//                    "                    },\n" +
//                    "                    {\n" +
//                    "                        \"question\": \"Иное\",\n" +
//                    "                        \"answer\": \"Нет\"\n" +
//                    "                    }\n" +
//                    "                ]\n" +
//                    "            }\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "            \"question\": \"В качестве ядра системы видеонаблюдения будет использоваться (сервер / видеорегистратор)\",\n" +
//                    "            \"answer\": {\n" +
//                    "                \"simpleAnswer\": \"На усмотрение проектировщика\",\n" +
//                    "                \"multipleAnswer\": []\n" +
//                    "            }\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "            \"question\": \"Укажите место расположения центрального оборудования\",\n" +
//                    "            \"answer\": {\n" +
//                    "                \"simpleAnswer\": \"Серверная (отмечена на планировках)\",\n" +
//                    "                \"multipleAnswer\": []\n" +
//                    "            }\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "            \"question\": \"Требуется ли функция просмотра с места размещения видеорегистратора/сервера (если да, то на каком количестве мониторов)\",\n" +
//                    "            \"answer\": {\n" +
//                    "                \"simpleAnswer\": \"Нет, просмотр будет на месте размещения оператора системы\",\n" +
//                    "                \"multipleAnswer\": []\n" +
//                    "            }\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "            \"question\": \"Укажите место расположения оборудования оператора системы\",\n" +
//                    "            \"answer\": {\n" +
//                    "                \"simpleAnswer\": \"Пост охраны (отмечен на планировках)\",\n" +
//                    "                \"multipleAnswer\": []\n" +
//                    "            }\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "            \"question\": \"Функционал ПО, доступный с АРМ\",\n" +
//                    "            \"answer\": {\n" +
//                    "                \"simpleAnswer\": \"Мониторинг и администрирование\",\n" +
//                    "                \"multipleAnswer\": []\n" +
//                    "            }\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "            \"question\": \"Укажите место расположения периферийного сетевого оборудования (в помещениях или на улице, если требуется)\",\n" +
//                    "            \"answer\": {\n" +
//                    "                \"simpleAnswer\": \"Определить проектом\",\n" +
//                    "                \"multipleAnswer\": []\n" +
//                    "            }\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "            \"question\": \"Укажите количество дней хранения архива\",\n" +
//                    "            \"answer\": {\n" +
//                    "                \"simpleAnswer\": \"30\",\n" +
//                    "                \"multipleAnswer\": []\n" +
//                    "            }\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "            \"question\": \"Осуществляем непрерывную запись (24 часа в сутки) или запись по движению, по событию (12-14 часов в сутки)\",\n" +
//                    "            \"answer\": {\n" +
//                    "                \"simpleAnswer\": \"По движению\",\n" +
//                    "                \"multipleAnswer\": []\n" +
//                    "            }\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "            \"question\": \"Укажите необходимую частоту кадров в секунду\",\n" +
//                    "            \"answer\": {\n" +
//                    "                \"simpleAnswer\": \"24\",\n" +
//                    "                \"multipleAnswer\": []\n" +
//                    "            }\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "            \"question\": \"Укажите желаемое разрешение записи\",\n" +
//                    "            \"answer\": {\n" +
//                    "                \"simpleAnswer\": \"2МП\",\n" +
//                    "                \"multipleAnswer\": []\n" +
//                    "            }\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "            \"question\": \"Укажите предпочитаемый видеокодек (H.264, H.265, MJPEG)\",\n" +
//                    "            \"answer\": {\n" +
//                    "                \"simpleAnswer\": \"H.265\",\n" +
//                    "                \"multipleAnswer\": []\n" +
//                    "            }\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "            \"question\": \"Если необходим резерв работы системы по питанию, то на какое время\",\n" +
//                    "            \"answer\": {\n" +
//                    "                \"simpleAnswer\": \"15 минут\",\n" +
//                    "                \"multipleAnswer\": []\n" +
//                    "            }\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "            \"question\": \"Требуется ли интеграция с системами Безопасный Город или Безопасный Регион\",\n" +
//                    "            \"answer\": {\n" +
//                    "                \"simpleAnswer\": \"Нет\",\n" +
//                    "                \"multipleAnswer\": []\n" +
//                    "            }\n" +
//                    "        },\n" +
//                    "        {\n" +
//                    "            \"question\": \"Укажите при необходимости дополнительные требования к системе СВН (запись звука, использование тревожных вх/вых, ИК-подсветка, интеграция с др. системами объекта и т.д.)\",\n" +
//                    "            \"answer\": {\n" +
//                    "                \"simpleAnswer\": \"Нет\",\n" +
//                    "                \"multipleAnswer\": []\n" +
//                    "            }\n" +
//                    "        }\n" +
//                    "    ]\n" +
//                    "}";
//            ObjectMapper om = new ObjectMapper();
//            try {
//
//            } catch (Exception e) {
//                System.err.println(e);
//            }
//            Questionnaire json01 = om.readValue(jsonString01, Questionnaire.class);




// -------  Add tasks

            Task task01 = new Task(
                    1L,
                    "somecustomer01@gmail.com",
                    TaskStatus.IN_HEAP,
                    TaskComplexity.LEVEL_1,
                    5L,
                    null,
                    null,
                    //null,
                    LocalDate.of(2022, 2, 16));
            Task task02 = new Task(
                    2L,
                    "somecustomer02@gmail.com",
                    TaskStatus.IN_HEAP,
                    TaskComplexity.LEVEL_1,
                    2L,
                    null,
                    null,
                    //null,
                    LocalDate.of(2022, 1, 30));
            List<Task> taskList = new ArrayList<>();
            taskList.add(task01);
            taskList.add(task02);
            taskRepository.saveAll(taskList);

// -------  Add comments
            TaskComment taskComment01 = new TaskComment(
                    1L,
                    2L,
                    1L,
                    LocalDate.of(2022, 5, 10),
                    "Инициализация заявки"
            );
            TaskComment taskComment02 = new TaskComment(
                    2L,
                    2L,
                    1L,
                    LocalDate.of(2022, 5, 10),
                    "Обновление информации"
            );
            TaskComment taskComment03 = new TaskComment(
                    3L,
                    4L,
                    1L,
                    LocalDate.of(2022, 5, 11),
                    "Вопрос по заявке"
            );
            TaskComment taskComment04 = new TaskComment(
                    4L,
                    2L,
                    1L,
                    LocalDate.of(2022, 5, 12),
                    "Ответ по заявке"
            );
            List<TaskComment> taskCommentList = new ArrayList<>();
            taskCommentList.add(taskComment01);
            taskCommentList.add(taskComment02);
            taskCommentList.add(taskComment03);
            taskCommentList.add(taskComment04);
            taskCommentRepository.saveAll(taskCommentList);

        };
    }
}
