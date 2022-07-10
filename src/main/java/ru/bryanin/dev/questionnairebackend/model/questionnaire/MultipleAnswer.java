package ru.bryanin.dev.questionnairebackend.model.questionnaire;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MultipleAnswer{
    String question;
    String answer;

    //@JsonProperty("question")
    public String getQuestion() {
        return this.question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }

    //@JsonProperty("answer")
    public String getAnswer() {
        return this.answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
