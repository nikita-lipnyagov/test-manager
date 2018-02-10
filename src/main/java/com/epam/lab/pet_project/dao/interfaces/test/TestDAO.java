package com.epam.lab.pet_project.dao.interfaces.test;

import com.epam.lab.pet_project.entities.tests.Test;

import java.sql.SQLException;
import java.util.List;

public interface TestDAO {
    Test findById(long id, String language) throws SQLException;

    List<String> findAllSubjects(String language) throws SQLException;

    List<Test> findFilteredTests(String subject, String orderedColumns, String language) throws SQLException;

    long createNewTest(String testName, String russianTestName, String subject, String level, String language, int questions, int timeLimit) throws SQLException;

    long doesTestAlreadyExist(String russianTestName) throws SQLException;

    boolean willItTestLanguageCopy(String russianTestName, String language) throws SQLException;

    void createTestTranslation(long numberOfTest, String testName, String russianTestName, String subject, String level, String language, int questions, int timeLimit) throws SQLException;

    List<String> findAllTestNames(String language) throws SQLException;

    long findIdByTestName(String testName) throws SQLException;
}
