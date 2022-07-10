package ru.bryanin.dev.questionnairebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.bryanin.dev.questionnairebackend.model.questionnaire.Questionnaire;

import java.util.List;
//
//@Repository
//public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Long> {
//
//    @Query("SELECT q from Questionnaire q left join Item i on q.Id=i.Id")
//    List<Questionnaire> findAllQuestionnairesWithItems();
//
//}
