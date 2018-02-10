package com.epam.lab.pet_project.dao.interfaces.test;

import com.epam.lab.pet_project.entities.tests.Question;

import java.sql.SQLException;
import java.util.List;

public interface QuestionDAO {
    List<Question> findAllQuestionsInTest(String language, long testId) throws SQLException;

    long doesQuestionAlreadyExist(String russianQuestion) throws SQLException;

    long createNewQuestion(String question, String russianQuestion, String language, int rightAnswer, int testId) throws SQLException;

    boolean willItQuestionLanguageCopy(String russianQuestion, String language) throws SQLException;

    void createQuestionTranslation(long numberOfQuestion, String question, String russianQuestion, String language, int rightAnswer, int testId) throws SQLException;

    long findIdByQuestionName(String question) throws SQLException;

    List<String> findAllQuestionNames(String language) throws SQLException;
}
