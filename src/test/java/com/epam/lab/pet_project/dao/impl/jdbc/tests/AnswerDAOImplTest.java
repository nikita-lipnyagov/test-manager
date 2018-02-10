package com.epam.lab.pet_project.dao.impl.jdbc.tests;

import com.epam.lab.pet_project.dao.impl.jdbc.H2CreateDataBase;
import com.epam.lab.pet_project.entities.tests.Answer;

import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.*;

public class AnswerDAOImplTest{

    @org.junit.Test
    public void findAll_English_Answers() throws Exception {
        //WHEN
        H2CreateDataBase h2CreateDataBase = new H2CreateDataBase();
        Connection connection = h2CreateDataBase.createH2Connection();
        AnswerDAOImpl answerDAO = new AnswerDAOImpl(connection);
        //THEN
        List<Answer> answers = answerDAO.findAll("ENG");
        h2CreateDataBase.cleanDataBase();
        assertEquals(answers.size(), 35);
    }

    @org.junit.Test
    public void findAll_Russian_Answers() throws Exception {
        //WHEN
        H2CreateDataBase h2CreateDataBase = new H2CreateDataBase();
        Connection connection = h2CreateDataBase.createH2Connection();
        AnswerDAOImpl answerDAO = new AnswerDAOImpl(connection);
        //THEN
        List<Answer> answers = answerDAO.findAll("RUS");
        h2CreateDataBase.cleanDataBase();
        assertEquals(answers.size(), 35);
    }

    @org.junit.Test
    public void findAllAnswersInQuestion() throws Exception {
        //WHEN
        H2CreateDataBase h2CreateDataBase = new H2CreateDataBase();
        Connection connection = h2CreateDataBase.createH2Connection();
        AnswerDAOImpl answerDAO = new AnswerDAOImpl(connection);
        List<Answer> answers = answerDAO.findAllAnswersInQuestion("ENG", 1);
        h2CreateDataBase.cleanDataBase();
        //THEN
        assertEquals(answers.size(), 3);
    }

    @org.junit.Test
    public void createNewAnswer() throws Exception {
        //WHEN
        H2CreateDataBase h2CreateDataBase = new H2CreateDataBase();
        Connection connection = h2CreateDataBase.createH2Connection();
        AnswerDAOImpl answerDAO = new AnswerDAOImpl(connection);
        //THEN
        answerDAO.createNewAnswer("answer", 1, "ENG");
        List<Answer> answers = answerDAO.findAll("ENG");
        h2CreateDataBase.cleanDataBase();
        assertEquals(answers.size(), 36);
    }

    @org.junit.Test
    public void getTableName() throws Exception {
        AnswerDAOImpl answerDAO = new AnswerDAOImpl();
        assertEquals(answerDAO.getTableName(), "answers");
    }

}