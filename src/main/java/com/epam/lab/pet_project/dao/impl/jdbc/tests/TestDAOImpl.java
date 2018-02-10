package com.epam.lab.pet_project.dao.impl.jdbc.tests;

import com.epam.lab.pet_project.dao.interfaces.test.TestDAO;
import com.epam.lab.pet_project.dao.impl.jdbc.DBSchema;
import com.epam.lab.pet_project.entities.tests.Question;
import com.epam.lab.pet_project.entities.tests.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestDAOImpl extends AbstractTestDAOImpl implements TestDAO {
    private final QuestionDAOImpl questionDAO = new QuestionDAOImpl();
    private static final Logger LOG = LoggerFactory.getLogger(TestDAOImpl.class);

    public TestDAOImpl() {
    }

    public TestDAOImpl(Connection connection) {
        setConnection(connection);
    }

    public List<Test> findAll(String language) throws SQLException {
        return (List<Test>) super.findAll(language);
    }

    @Override
    public Test findById(long id, String language) throws SQLException {
        Connection connection = getConnection();
        String query = String.format("SELECT * FROM %s WHERE %s = %d AND %s = '%s'",
                getTableName(),
                DBSchema.TESTS.NUMBER,
                id,
                DBSchema.TESTS.LANGUAGE,
                language);
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            List<Test> tests = processResultSet(resultSet);
            if (tests.isEmpty()) {
                return null;
            }
            LOG.debug("Return test");
            return tests.get(0);

        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return null;
    }

    @Override
    protected List<Test> processResultSet(ResultSet resultSet) throws SQLException {
        List<Test> result = new ArrayList<>();
        while (resultSet.next()) {
            long testId = resultSet.getLong(DBSchema.TESTS.NUMBER);
            String testName = resultSet.getString(DBSchema.TESTS.TEST_NAME);
            String subject = resultSet.getString(DBSchema.TESTS.SUBJECT);
            String level = resultSet.getString(DBSchema.TESTS.LEVEL);
            int numberOfQuestions = resultSet.getInt(DBSchema.TESTS.NUMBER_OD_QUESTIONS);
            long timeLimit = resultSet.getLong(DBSchema.TESTS.TIME_LIMIT);
            String language = resultSet.getString(DBSchema.TESTS.LANGUAGE);

            ArrayList<Question> questions = new ArrayList<>(questionDAO.findAllQuestionsInTest(language, testId));

            Test test = new Test(testName, subject, level, timeLimit, language, numberOfQuestions, questions);
            test.setTestId(testId);
            result.add(test);
        }
        resultSet.close();
        LOG.debug("process new test entities");
        return result;
    }

    @Override
    protected String getTableName() {
        return DBSchema.TESTS.name();
    }

    @Override
    public List<String> findAllSubjects(String language) throws SQLException {
        List<String> subjects = new ArrayList<>();
        Connection connection = getConnection();
        String query = String.format("SELECT DISTINCT %s FROM %s WHERE %s = '%s'",
                DBSchema.TESTS.SUBJECT,
                getTableName(),
                DBSchema.TESTS.LANGUAGE,
                language);
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                LOG.debug("Return subjects");
                subjects.add(resultSet.getString(DBSchema.TESTS.SUBJECT));
            }
            return subjects;
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return null;
    }

    @Override
    public List<Test> findFilteredTests(String subject, String orderedColumns, String language) throws SQLException {
        String query = null;
        if (subject != null) {
            query = String.format("SELECT * FROM %s WHERE %s = '%s'",
                    getTableName(),
                    DBSchema.TESTS.SUBJECT,
                    subject);
        } else {
            query = String.format("SELECT * FROM %s WHERE %s = '%s'",
                    getTableName(),
                    DBSchema.TESTS.LANGUAGE,
                    language);
        }

        if (!orderedColumns.equals("")) {
            query += " ORDER BY " + orderedColumns;
        }
        LOG.debug("Filter tests");
        return (List<Test>) executeQuery(query);
    }

    @Override
    public long createNewTest(String testName, String russianTestName, String subject, String level, String language, int questions, int timeLimit) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("INSERT INTO " + DBSchema.TESTS.name() +
                    "( " + DBSchema.TESTS.NUMBER + ", " + DBSchema.TESTS.TEST_NAME + ", " + DBSchema.TESTS.RUSSIAN_NAME +
                    ", " + DBSchema.TESTS.SUBJECT + ", " + DBSchema.TESTS.LEVEL + ", "
                    + DBSchema.TESTS.LANGUAGE + ", " + DBSchema.TESTS.NUMBER_OD_QUESTIONS
                    + ", " + DBSchema.TESTS.TIME_LIMIT + ")" + " VALUES(?, ?, ?, ?, ?, ?, ?, ?) ");
            int numberOfTest = (count() / 2) + 1;
            statement.setInt(1, numberOfTest);
            statement.setString(2, testName);
            statement.setString(3, russianTestName);
            statement.setString(4, subject);
            statement.setString(5, level);
            statement.setString(6, language);
            statement.setInt(7, questions);
            statement.setInt(8, timeLimit);

            statement.executeUpdate();
            LOG.debug("New test was created");
            return numberOfTest;

        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }finally {
            if(statement != null){
                statement.close();
            }
        }
        return -1;
    }

    @Override
    public long doesTestAlreadyExist(String russianTestName) throws SQLException {
        String query = String.format("SELECT %s FROM %s WHERE %s = '%s'",
                DBSchema.TESTS.NUMBER,
                DBSchema.TESTS.name(),
                DBSchema.TESTS.RUSSIAN_NAME,
                russianTestName);
        LOG.debug("Method doesTestAlreadyExist if test exists, returns number of test");
        return executeQueryReturnId(query);
    }

    @Override
    public boolean willItTestLanguageCopy(String russianTestName, String language) throws SQLException {
        String query = String.format("SELECT %s FROM %s WHERE %s = '%s' AND %s = '%s'",
                DBSchema.TESTS.NUMBER,
                DBSchema.TESTS.name(),
                DBSchema.TESTS.RUSSIAN_NAME,
                russianTestName,
                DBSchema.TESTS.LANGUAGE,
                language);
        LOG.debug("Test will it be language copy");
        return executeQueryWillItBeEntityCopy(query);
    }

    @Override
    public void createTestTranslation(long numberOfTest, String testName, String russianTestName, String subject, String level, String language, int questions, int timeLimit) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("INSERT INTO " + DBSchema.TESTS.name() +
                    "( " + DBSchema.TESTS.NUMBER + ", " + DBSchema.TESTS.TEST_NAME + ", " + DBSchema.TESTS.RUSSIAN_NAME +
                    ", " + DBSchema.TESTS.SUBJECT + ", " + DBSchema.TESTS.LEVEL + ", "
                    + DBSchema.TESTS.LANGUAGE + ", " + DBSchema.TESTS.NUMBER_OD_QUESTIONS
                    + ", " + DBSchema.TESTS.TIME_LIMIT + ")" + " VALUES(?, ?, ?, ?, ?, ?, ?, ?) ", Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, numberOfTest);
            statement.setString(2, testName);
            statement.setString(3, russianTestName);
            statement.setString(4, subject);
            statement.setString(5, level);
            statement.setString(6, language);
            statement.setInt(7, questions);
            statement.setInt(8, timeLimit);

            statement.executeUpdate();
            LOG.debug("Test translation was created");
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }finally {
            if(statement != null){
                statement.close();
            }
        }
    }

    @Override
    public List<String> findAllTestNames(String language) throws SQLException {
        List<String> testNames = new ArrayList<>();
        Connection connection = getConnection();
        String query = String.format("SELECT DISTINCT %s FROM %s WHERE %s = '%s'",
                DBSchema.TESTS.TEST_NAME,
                getTableName(),
                DBSchema.TESTS.LANGUAGE,
                language);
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                testNames.add(resultSet.getString(DBSchema.TESTS.TEST_NAME));
            }
            LOG.debug("return all test names");
            return testNames;
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return null;
    }

    @Override
    public long findIdByTestName(String testName) throws SQLException {
        String query = String.format("SELECT %s FROM %s WHERE %s = '%s'",
                DBSchema.TESTS.NUMBER,
                DBSchema.TESTS.name(),
                DBSchema.TESTS.TEST_NAME,
                testName);
        LOG.debug("Return test id");
        return executeQueryReturnId(query);
    }
}
