package com.epam.lab.pet_project.dao.impl.jdbc.tests;

import com.epam.lab.pet_project.dao.impl.jdbc.H2CreateDataBase;
import com.epam.lab.pet_project.entities.tests.Question;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.*;

public class QuestionDAOImplTest {
    @Test
    public void findAll() throws Exception {
        //WHEN
        H2CreateDataBase h2CreateDataBase = new H2CreateDataBase();
        Connection connection = h2CreateDataBase.createH2Connection();
        QuestionDAOImpl questionDAO = new QuestionDAOImpl(connection);
        //THEN
        List<Question> questions = questionDAO.findAll("ENG");
        h2CreateDataBase.cleanDataBase();
        assertEquals(questions.size(), 13);
    }

    @Test
    public void findAllQuestionsInTest() throws Exception {
        //WHEN
        H2CreateDataBase h2CreateDataBase = new H2CreateDataBase();
        Connection connection = h2CreateDataBase.createH2Connection();
        QuestionDAOImpl questionDAO = new QuestionDAOImpl(connection);
        //THEN
        List<Question> questions = questionDAO.findAllQuestionsInTest("RUS", 1);
        h2CreateDataBase.cleanDataBase();
        assertEquals(questions.size(), 5);
    }

    @Test
    public void doesQuestionAlreadyExist() throws Exception {
        //WHEN
        H2CreateDataBase h2CreateDataBase = new H2CreateDataBase();
        Connection connection = h2CreateDataBase.createH2Connection();
        QuestionDAOImpl questionDAO = new QuestionDAOImpl(connection);
        //THEN
        long questionId = questionDAO.doesQuestionAlreadyExist("Сколько будет 12*12 ?   ");
        h2CreateDataBase.cleanDataBase();
        assertEquals(questionId, 6);
    }

    @Test
    public void createNewQuestion() throws Exception {
        //WHEN
        H2CreateDataBase h2CreateDataBase = new H2CreateDataBase();
        Connection connection = h2CreateDataBase.createH2Connection();
        QuestionDAOImpl questionDAO = new QuestionDAOImpl(connection);
        //THEN
        questionDAO.createNewQuestion("question","вопрос","ENG", 1, 1);
        List<Question> questions = questionDAO.findAll("ENG");
        h2CreateDataBase.cleanDataBase();
        assertEquals(questions.size(), 14);
    }

    @Test
    public void willItQuestionLanguageCopy_Copy_Exists() throws Exception {
        //WHEN
        H2CreateDataBase h2CreateDataBase = new H2CreateDataBase();
        Connection connection = h2CreateDataBase.createH2Connection();
        QuestionDAOImpl questionDAO = new QuestionDAOImpl(connection);
        //THEN
        questionDAO.createNewQuestion("question","вопрос","ENG", 1, 1);
        questionDAO.createNewQuestion("question","вопрос","ENG", 1, 1);
        boolean willItQuestionLanguageCopy = questionDAO.willItQuestionLanguageCopy("вопрос", "ENG");
        h2CreateDataBase.cleanDataBase();
        assertEquals(willItQuestionLanguageCopy, true);
    }

    @Test
    public void createQuestionTranslation() throws Exception {
        //WHEN
        H2CreateDataBase h2CreateDataBase = new H2CreateDataBase();
        Connection connection = h2CreateDataBase.createH2Connection();
        QuestionDAOImpl questionDAO = new QuestionDAOImpl(connection);
        //THEN
        questionDAO.createNewQuestion("question","вопрос","ENG", 1, 1);
        questionDAO.createQuestionTranslation(14, "вопрос","вопрос","RUS", 1, 1);
        List<Question> questions = questionDAO.findAll("RUS");
        h2CreateDataBase.cleanDataBase();
        assertEquals(questions.size(), 14);
    }

    @Test
    public void findIdByQuestionName() throws Exception {
        //WHEN
        H2CreateDataBase h2CreateDataBase = new H2CreateDataBase();
        Connection connection = h2CreateDataBase.createH2Connection();
        QuestionDAOImpl questionDAO = new QuestionDAOImpl(connection);
        //THEN
        long questionId = questionDAO.findIdByQuestionName("How much will be 13 * 16 ?");
        h2CreateDataBase.cleanDataBase();
        assertEquals(questionId, 8);
    }

    @Test
    public void findAllQuestionNames() throws Exception {
        //WHEN
        H2CreateDataBase h2CreateDataBase = new H2CreateDataBase();
        Connection connection = h2CreateDataBase.createH2Connection();
        QuestionDAOImpl questionDAO = new QuestionDAOImpl(connection);
        //THEN
        List<String> names = questionDAO.findAllQuestionNames("ENG");
        h2CreateDataBase.cleanDataBase();
        assertEquals(names.size(), 13);
    }

    @Test
    public void getTableName() throws Exception {
        //WHEN
        QuestionDAOImpl questionDAO = new QuestionDAOImpl();
        //THEN
        assertEquals(questionDAO.getTableName(), "questions");
    }

}