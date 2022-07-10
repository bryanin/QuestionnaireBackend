package ru.bryanin.dev.questionnairebackend.model.questionnaire;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Content{
    String question;
    Answer answer;

    //@JsonProperty("question")
    public String getQuestion() {
        return this.question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }

    //@JsonProperty("answer")
    public Answer getAnswer() {
        return this.answer;
    }
    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

}
