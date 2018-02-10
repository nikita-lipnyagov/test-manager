package com.epam.lab.pet_project.dao.impl.jdbc.tests;

import com.epam.lab.pet_project.dao.impl.jdbc.H2CreateDataBase;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.*;

public class TestDAOImplTest {
    @Test
    public void findAll() throws Exception {
        //WHEN
        H2CreateDataBase h2CreateDataBase = new H2CreateDataBase();
        Connection connection = h2CreateDataBase.createH2Connection();
        TestDAOImpl testDAO = new TestDAOImpl(connection);
        //THEN
        List<com.epam.lab.pet_project.entities.tests.Test> tests = testDAO.findAll("ENG");
        h2CreateDataBase.cleanDataBase();
        assertEquals(tests.size(), 3);
    }

    @Test
    public void findById() throws Exception {
        //WHEN
        H2CreateDataBase h2CreateDataBase = new H2CreateDataBase();
        Connection connection = h2CreateDataBase.createH2Connection();
        TestDAOImpl testDAO = new TestDAOImpl(connection);
        //THEN
        com.epam.lab.pet_project.entities.tests.Test test = testDAO.findById(1, "ENG");
        h2CreateDataBase.cleanDataBase();
        assertEquals(test.getTestName(), "Numerical series");
    }

    @Test
    public void getTableName() throws Exception {
        //WHEN
        TestDAOImpl testDAO = new TestDAOImpl();
        //THEN
        assertEquals(testDAO.getTableName(), "tests");
    }

    @Test
    public void findAllSubjects() throws Exception {
        //WHEN
        H2CreateDataBase h2CreateDataBase = new H2CreateDataBase();
        Connection connection = h2CreateDataBase.createH2Connection();
        TestDAOImpl testDAO = new TestDAOImpl(connection);
        //THEN
        List<String> subjects = testDAO.findAllSubjects("ENG");
        h2CreateDataBase.cleanDataBase();
        assertEquals(subjects.size(), 2);
    }

    @Test
    public void createNewTest() throws Exception {
        //WHEN
        H2CreateDataBase h2CreateDataBase = new H2CreateDataBase();
        Connection connection = h2CreateDataBase.createH2Connection();
        TestDAOImpl testDAO = new TestDAOImpl(connection);
        //THEN
        testDAO.createNewTest("test", "тест","logic", "EASY", "ENG", 10, 100);
        List<com.epam.lab.pet_project.entities.tests.Test> tests = testDAO.findAll("ENG");
        h2CreateDataBase.cleanDataBase();
        assertEquals(tests.size(), 4);
    }

    @Test
    public void doesTestAlreadyExist() throws Exception {
        //WHEN
        H2CreateDataBase h2CreateDataBase = new H2CreateDataBase();
        Connection connection = h2CreateDataBase.createH2Connection();
        TestDAOImpl testDAO = new TestDAOImpl(connection);
        //THEN
        long id = testDAO.doesTestAlreadyExist("Арифметика");
        h2CreateDataBase.cleanDataBase();
        assertEquals(id, 2);
    }

    @Test
    public void willItTestLanguageCopy() throws Exception {
        //WHEN
        H2CreateDataBase h2CreateDataBase = new H2CreateDataBase();
        Connection connection = h2CreateDataBase.createH2Connection();
        TestDAOImpl testDAO = new TestDAOImpl(connection);
        testDAO.createNewTest("test", "тест","logic", "EASY", "ENG", 10, 100);
        testDAO.createNewTest("test", "тест","logic", "EASY", "ENG", 10, 100);
        //THEN
        boolean willItTestLanguageCopy = testDAO.willItTestLanguageCopy("тест", "ENG");
        h2CreateDataBase.cleanDataBase();
        assertEquals(willItTestLanguageCopy, true);
    }

    @Test
    public void createTestTranslation() throws Exception {
        //WHEN
        H2CreateDataBase h2CreateDataBase = new H2CreateDataBase();
        Connection connection = h2CreateDataBase.createH2Connection();
        TestDAOImpl testDAO = new TestDAOImpl(connection);
        testDAO.createNewTest("test", "тест","logic", "EASY", "ENG", 10, 100);
        testDAO.createTestTranslation(4, "тест", "тест","logic", "EASY", "RUS", 10, 100);
        //THEN
        List<com.epam.lab.pet_project.entities.tests.Test> tests = testDAO.findAll("RUS");
        h2CreateDataBase.cleanDataBase();
        assertEquals(tests.size(), 4);
    }

    @Test
    public void findAllTestNames() throws Exception {
        //WHEN
        H2CreateDataBase h2CreateDataBase = new H2CreateDataBase();
        Connection connection = h2CreateDataBase.createH2Connection();
        TestDAOImpl testDAO = new TestDAOImpl(connection);
        //THEN
        List<String> testNames = testDAO.findAllTestNames("RUS");
        h2CreateDataBase.cleanDataBase();
        assertEquals(testNames.size(), 3);
    }

    @Test
    public void findIdByTestName() throws Exception {
        //WHEN
        H2CreateDataBase h2CreateDataBase = new H2CreateDataBase();
        Connection connection = h2CreateDataBase.createH2Connection();
        TestDAOImpl testDAO = new TestDAOImpl(connection);
        //THEN
        long idByTestName = testDAO.findIdByTestName("Arithmetics");
        h2CreateDataBase.cleanDataBase();
        assertEquals(idByTestName, 2);
    }

}