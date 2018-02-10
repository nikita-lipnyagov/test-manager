package com.epam.lab.pet_project.entities.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class Question {
    private static final Logger LOG = LoggerFactory.getLogger(Question.class);

    long questionId;
    String question;
    int ringhtAnswer;
    String language;
    ArrayList<Answer> answers;

    public Question(String question, int ringhtAnswer,
                    String language, ArrayList<Answer> answers) {
        this.question = question;
        this.ringhtAnswer = ringhtAnswer;
        this.language = language;
        this.answers = answers;

        LOG.debug("new Question was created");
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getRinghtAnswer() {
        return ringhtAnswer;
    }

    public void setRinghtAnswer(int ringhtAnswer) {
        this.ringhtAnswer = ringhtAnswer;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }
}
