package com.epam.lab.pet_project.entities.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Answer {
    private static final Logger LOG = LoggerFactory.getLogger(Answer.class);

    long answerId;
    String answer;
    String language;

    public Answer(String answer, String language) {
        this.answer = answer;
        this.language = language;
        LOG.debug("new Answer was created");
    }

    public long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
