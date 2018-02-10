package com.epam.lab.pet_project.services;

import com.epam.lab.pet_project.dao.impl.jdbc.tests.AnswerDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class AnswerService {
    private static final Logger LOG = LoggerFactory.getLogger(AnswerService.class);
    private final AnswerDAOImpl answerDAO = new AnswerDAOImpl();

    public void createNewAnswer(String questionText, long questionId, String language) {
        try {
            answerDAO.createNewAnswer(questionText, questionId, language);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
    }
}
