package ru.bryanin.dev.questionnairebackend.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.bryanin.dev.questionnairebackend.model.project.Project;
import ru.bryanin.dev.questionnairebackend.model.task.*;
import ru.bryanin.dev.questionnairebackend.model.task.System;
import ru.bryanin.dev.questionnairebackend.model.user.BasicUser;
import ru.bryanin.dev.questionnairebackend.model.user.Position;
import ru.bryanin.dev.questionnairebackend.security.SecurityRole;
import ru.bryanin.dev.questionnairebackend.security.AccessStatus;
import ru.bryanin.dev.questionnairebackend.repository.BasicUserRepository;
import ru.bryanin.dev.questionnairebackend.repository.ProjectRepository;
import ru.bryanin.dev.questionnairebackend.repository.TaskCommentRepository;
import ru.bryanin.dev.questionnairebackend.repository.TaskRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class InitialConfig {

    @Bean
    CommandLineRunner commandLineRunner(BasicUserRepository basicUserRepository, ProjectRepository projectRepository, TaskRepository taskRepository, TaskCommentRepository taskCommentRepository) {
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

            Task task01 = new Task(
                    1L,
                    "somecustomer01@gmail.com",
                    TaskStatus.IN_HEAP,
                    TaskComplexity.LEVEL_1,
                    System.AUPT,
                    null,
                    null,
                    null,
                    "{\n" +
                            "  \"Предпочитаемое ГОТВ\" : \"\",\n" +
                            "  \"Высота над уровнем моря, м\" : \"\",\n" +
                            "  \"Резерв (да/нет)\" : \"\",\n" +
                            "  \"Допустимая нагрузка на стены, Па\" : \"\",\n" +
                            "  \"Автоматику пожаротушения проектируем? Если да, то указать бренд\" : \"\",\n" +
                            "  \"Если автоматику проектируем, то указать, куда передаем сигналы в ПС (указать пост охраны ПС)\" : \"\",\n" +
                            "  \"Помещения\" : [\n" +
                            "    {\n" +
                            "      \"Номер помещения согласно экспликации\" : \"\",\n" +
                            "      \"Функциональное назначение помещения\" : \"\",\n" +
                            "      \"Вид пожарной нагрузки (класс пожара)\" : \"\",\n" +
                            "      \"Подвесной потолок, м\" : \"\",\n" +
                            "      \"Фальшпол, м\" : \"\",\n" +
                            "      \"Площадь, м2\" : \"\",\n" +
                            "      \"Высота от бетона до бетона, м\" : \"\",\n" +
                            "      \"Местонахождение модулей, если необходим гидравлический расчет\" : \"\",\n" +
                            "      \"Минимальная температура в помещении, °С\" : \"\",\n" +
                            "      \"Максимальная температура в помещении, °С\" : \"\"\n" +
                            "    },\n" +
                            "    {\n" +
                            "      \"Номер помещения согласно экспликации\" : \"\",\n" +
                            "      \"Функциональное назначение помещения\" : \"\",\n" +
                            "      \"Вид пожарной нагрузки (класс пожара)\" : \"\",\n" +
                            "      \"Подвесной потолок, м\" : \"\",\n" +
                            "      \"Фальшпол, м\" : \"\",\n" +
                            "      \"Площадь, м2\" : \"\",\n" +
                            "      \"Высота от бетона до бетона, м\" : \"\",\n" +
                            "      \"Местонахождение модулей, если необходим гидравлический расчет\" : \"\",\n" +
                            "      \"Минимальная температура в помещении, °С\" : \"\",\n" +
                            "      \"Максимальная температура в помещении, °С\" : \"\"\n" +
                            "    },\n" +
                            "    {\n" +
                            "      \"Номер помещения согласно экспликации\" : \"\",\n" +
                            "      \"Функциональное назначение помещения\" : \"\",\n" +
                            "      \"Вид пожарной нагрузки (класс пожара)\" : \"\",\n" +
                            "      \"Подвесной потолок, м\" : \"\",\n" +
                            "      \"Фальшпол, м\" : \"\",\n" +
                            "      \"Площадь, м2\" : \"\",\n" +
                            "      \"Высота от бетона до бетона, м\" : \"\",\n" +
                            "      \"Местонахождение модулей, если необходим гидравлический расчет\" : \"\",\n" +
                            "      \"Минимальная температура в помещении, °С\" : \"\",\n" +
                            "      \"Максимальная температура в помещении, °С\" : \"\"\n" +
                            "    }\n" +
                            "  ]\n" +
                            "}");
            Task task02 = new Task(
                    2L,
                    "somecustomer02@gmail.com",
                    TaskStatus.IN_HEAP,
                    TaskComplexity.LEVEL_1,
                    System.SOT,
                    null,
                    null,
                    null,
                    "{\n" +
                            "  \"Какой тип системы ВН требуется организовать на объекте?\" : \"аналоговая/цифровая\",\n" +
                            "  \"Существует ли минимально допустимое разрешение (МП)\" : \"Нет\",\n" +
                            "  \"Камеры для внутреннего наблюдения установить в помещениях\" : \"Холлы, коридоры, входы на этаж и в здание\",\n" +
                            "  \"Укажите особый температурный режим в местах установки камер (например, отметьте, какие помещения неотапливаемые) /дополнительные требования к оборудованию (например, взрывозащищенное оборудование)\" : \"Нет\",\n" +
                            "  \"Способ прокладки кабельных линий к камерам в помещениях\" : \"В гофре и кабель-канале\",\n" +
                            "  \"Камеры для наружного наблюдения установить для наблюдения за объектами\" : \"По периметру здания и на въезде на территорию\",\n" +
                            "  \"Способ прокладки кабельных линий к уличным камерам\" : \"Воздушные линии (с применением троса), крепление к фасаду и столбам\",\n" +
                            "\n" +
                            "  \"Укажите, какие задачи системы видеонаблюдения должны выполняться\" : [\n" +
                            "    {\n" +
                            "      \"Обнаружение (позволяет определить с высокой степенью достоверности присутствие того или иного объекта в поле зрения камеры наблюдения)\" : \"По периметру здания\",\n" +
                            "      \"Распознавание (позволяет с высокой степенью уверенности заявить является ли текущий случай единичным или он уже был замечен ранее (тот же объект или другой))\" : \"Остальные помещения\",\n" +
                            "      \"Идентификация (детали должны быть достаточно различимы для идентификации личности человека вне всякого сомнения)\" : \"На входах в здание\"\n" +
                            "    }\n" +
                            "  ],\n" +
                            "  \"Необходимы ли функции видеоаналитики\" : [\n" +
                            "    {\n" +
                            "      \"Распознавание лиц\" : \"Нет\",\n" +
                            "      \"Распознавание ГРЗ (гос.рег.знаков)\" : \"Нет\",\n" +
                            "      \"Распознавание марки и модели ТС (транспортного средства)\" : \"Нет\",\n" +
                            "      \"Детектор нарушений (ПДД)\" : \"Нет\",\n" +
                            "      \"Детектор ношения маски\" : \"Нет\",\n" +
                            "      \"Детектор дыма и огня\" : \"Нет\",\n" +
                            "      \"Трекер объектов\" : \"Нет\",\n" +
                            "      \"Нейротрекер объектов\" : \"Нет\",\n" +
                            "      \"Подключение POS-терминала\" : \"Нет\",\n" +
                            "      \"Централизованные веб-отчеты\" : \"Нет\",\n" +
                            "      \"Интеграция со СКУД\" : \"Нет\",\n" +
                            "      \"Интеграция систем периметральной охраны\" : \"Нет\",\n" +
                            "      \"Интеграция с ЕЦХД (единый центр хранения данных)\" : \"Нет\",\n" +
                            "      \"Иное\" : \"Нет\"\n" +
                            "    }\n" +
                            "  ],\n" +
                            "  \"В качестве ядра системы видеонаблюдения будет использоваться (сервер / видеорегистратор)\" : \"На усмотрение проектировщика\",\n" +
                            "  \"Укажите место расположения центрального оборудования\" : \"Серверная (отмечен на планировках)\",\n" +
                            "  \"Требуется ли функция просмотра с места размещения видеорегистратора/сервера\" : \"Нет, просмотр будет на месте размещения оператора системы\",\n" +
                            "  \"Если функция просмотра с места размещения видеорегистратора/сервера требуется, то на каком количестве мониторов\" : \"Нет, просмотр будет на месте размещения оператора системы\",\n" +
                            "  \"Укажите место расположения оборудования оператора системы\" : \"Пост охраны (отмечен на планировках)\",\n" +
                            "  \"Функционал, доступный с данного АРМ\" : \"Мониторинг и администрирование\",\n" +
                            "  \"Количество каналов на данный АРМ\" : \"Определить проектом\",\n" +
                            "  \"Количество мониторов, используемых на данном АРМ\" : \"Определить проектом\",\n" +
                            "  \"Укажите место расположения периферийного сетевого оборудования (в помещениях и на улице, если требуется)\" : \"Определить проектом\",\n" +
                            "  \"Укажите количество дней хранения архива\" : \"30\",\n" +
                            "  \"Осуществляем непрерывную запись или запись по движению, по событию\" : \"По движению\",\n" +
                            "  \"Укажите необходимую частоту кадров в секунду\" : \"24\",\n" +
                            "  \"Укажите желаемое разрешение записи\" : \"2 МП\",\n" +
                            "  \"Укажите предпочитаемый видеокодек (H.264, H.265, MJPEG)\" : \"H.265\",\n" +
                            "  \"Если необходим резерв работы системы по питанию, то на какое время\" : \"15 минут\",\n" +
                            "  \"Требуется ли интеграция с системами Безопасный Город или Безопасный Регион\" : \"Нет\",\n" +
                            "  \"Укажите при необходимости дополнительные требования к системе СВН (запись звука, использование тревожных вх/вых, ИК-подсветка, интеграция с др. системами объекта и т.д.)\" : \"Интеграция со СКУД Parsec (15 контроллеров)\",\n" +
                            "\n" +
                            "}");
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
