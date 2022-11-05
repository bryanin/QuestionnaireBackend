package ru.bryanin.dev.questionnairebackend.customer.service;

import org.springframework.stereotype.Service;
import ru.bryanin.dev.questionnairebackend.customer.repository.QuestionnaireRepository;

@Service
public class QuestionnaireService {

    private final QuestionnaireRepository questionnaireRepository;

    public QuestionnaireService(QuestionnaireRepository questionnaireRepository) {
        this.questionnaireRepository = questionnaireRepository;
    }

    public String getQuestionnaireByTaskId(Long id) {
        return questionnaireRepository.findByTaskId(id).orElseThrow(() -> new IllegalStateException("Задачи с id = " + id + " не существует"));
    }
}
