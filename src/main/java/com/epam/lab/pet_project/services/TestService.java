package com.epam.lab.pet_project.services;

import com.epam.lab.pet_project.dao.impl.jdbc.tests.TestDAOImpl;
import com.epam.lab.pet_project.entities.tests.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class TestService {
    private static final Logger LOG = LoggerFactory.getLogger(TestService.class);
    private final TestDAOImpl testDAO = new TestDAOImpl();

    public List<Test> findAll(String language){
        try {
            return testDAO.findAll(language);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public List<String> findAllSubjects(String language) {
        try {
            return testDAO.findAllSubjects(language);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public List<Test> findFilteredTests(String subject, String orderedColumns, String language) {
        try {
            return testDAO.findFilteredTests(subject, orderedColumns, language);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public Test findById(long id,String language) {
        try {
            return testDAO.findById(id, language);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public long createNewTest(String testName, String russianTestName, String subject, String level, String language, int questions, int timeLimit) {
        try {
            return testDAO.createNewTest(testName, russianTestName, subject, level, language, questions, timeLimit);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }

    public long doesTestAlreadyExist(String russianTestName) {
        try {
            return testDAO.doesTestAlreadyExist(russianTestName);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }

    public void createTestTranslation(long numberOfTest, String testName, String russianTestName, String subject, String level, String language, int questions, int timeLimit) {
        try {
            testDAO.createTestTranslation(numberOfTest, testName, russianTestName, subject, level, language, questions, timeLimit);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean willItTestLanguageCopy(String russianTestName, String language) {
        try {
            return testDAO.willItTestLanguageCopy(russianTestName, language);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public List<String> findAllTestNames(String language) {
        try {
            return testDAO.findAllTestNames(language);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public long findIdByTestName(String testName) {
        try {
            return testDAO.findIdByTestName(testName);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }
}
