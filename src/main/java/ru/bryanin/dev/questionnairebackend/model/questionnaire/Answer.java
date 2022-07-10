package ru.bryanin.dev.questionnairebackend.model.questionnaire;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Answer{
    String simpleAnswer;
    ArrayList<MultipleAnswer> multipleAnswer;

    //@JsonProperty("simpleAnswer")
    public String getSimpleAnswer() {
        return this.simpleAnswer;
    }
    public void setSimpleAnswer(String simpleAnswer) {
        this.simpleAnswer = simpleAnswer;
    }


    //@JsonProperty("multipleAnswer")
    public ArrayList<MultipleAnswer> getMultipleAnswer() {
        return this.multipleAnswer;
    }
    public void setMultipleAnswer(ArrayList<MultipleAnswer> multipleAnswer) {
        this.multipleAnswer = multipleAnswer;
    }

}