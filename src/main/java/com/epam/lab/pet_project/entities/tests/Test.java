package com.epam.lab.pet_project.entities.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class Test {
    private static final Logger LOG = LoggerFactory.getLogger(Test.class);

    long testId;
    String testName;
    String subject;
    String level;
    long timeLimit;
    String language;
    int numberOfQuestions;
    ArrayList<Question> questions;

    public Test(String testName, String subject, String level,
                long timeLimit, String language, int numberOfQuestions, ArrayList<Question> questions) {
        this.testName = testName;
        this.subject = subject;
        this.level = level;
        this.timeLimit = timeLimit;
        this.language = language;
        this.numberOfQuestions = numberOfQuestions;
        this.questions = questions;

        LOG.debug("new Test was created");
    }

    public long getTestId() {
        return testId;
    }

    public void setTestId(long testId) {
        this.testId = testId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public long getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(long timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }
}
