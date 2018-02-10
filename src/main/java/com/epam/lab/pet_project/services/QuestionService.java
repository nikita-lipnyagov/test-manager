package com.epam.lab.pet_project.services;

import com.epam.lab.pet_project.dao.impl.jdbc.tests.QuestionDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class QuestionService {
    private static final Logger LOG = LoggerFactory.getLogger(QuestionService.class);
    private final QuestionDAOImpl questionDAO = new QuestionDAOImpl();


    public long doesQuestionAlreadyExist(String russianQuestion) {
        try {
            return questionDAO.doesQuestionAlreadyExist(russianQuestion);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }

    public long createNewQuestion(String question, String russianQuestion, String language, int rightAnswer, int testId) {
        try {
            return questionDAO.createNewQuestion(question, russianQuestion, language, rightAnswer, testId);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }

    public boolean willItQuestionLanguageCopy(String russianQuestion, String language) {
        try {
            return questionDAO.willItQuestionLanguageCopy(russianQuestion, language);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public void createQuestionTranslation(long numberOfQuestion, String question, String russianQuestion, String language, int rightAnswer, int testId) {
        try {
            questionDAO.createQuestionTranslation(numberOfQuestion, question, russianQuestion, language, rightAnswer, testId);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public long findIdByQuestionName(String question) {
        try {
            return questionDAO.findIdByQuestionName(question);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }

    public List<String> findAllQuestionNames(String language) {
        try {
            return questionDAO.findAllQuestionNames(language);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
