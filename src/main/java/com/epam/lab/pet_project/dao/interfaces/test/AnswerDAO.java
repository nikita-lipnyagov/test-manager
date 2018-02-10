package com.epam.lab.pet_project.dao.interfaces.test;

import com.epam.lab.pet_project.entities.tests.Answer;

import java.sql.SQLException;
import java.util.List;

public interface AnswerDAO {
    List<Answer> findAllAnswersInQuestion(String language, long questionId) throws SQLException;

    void createNewAnswer(String answerText, long questionId, String language) throws SQLException;
}
