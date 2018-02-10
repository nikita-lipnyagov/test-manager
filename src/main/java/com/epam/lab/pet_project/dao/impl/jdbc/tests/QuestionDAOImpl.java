package com.epam.lab.pet_project.dao.impl.jdbc.tests;

import com.epam.lab.pet_project.dao.interfaces.test.QuestionDAO;
import com.epam.lab.pet_project.dao.impl.jdbc.DBSchema;
import com.epam.lab.pet_project.entities.tests.Answer;
import com.epam.lab.pet_project.entities.tests.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAOImpl extends AbstractTestDAOImpl implements QuestionDAO {
    private final AnswerDAOImpl answerDAO = new AnswerDAOImpl();
    private static final Logger LOG = LoggerFactory.getLogger(QuestionDAOImpl.class);

    public QuestionDAOImpl() {
    }

    public QuestionDAOImpl(Connection connection) {
        setConnection(connection);
    }

    @Override
    public List<Question> findAll(String language) throws SQLException {
        return (List<Question>) super.findAll(language);
    }

    @Override
    protected List<Question> processResultSet(ResultSet resultSet) throws SQLException {
        List<Question> result = new ArrayList<>();
        while (resultSet.next()) {
            long questionId = resultSet.getLong(DBSchema.QUESTIONS.NUMBER);
            String textQuestion = resultSet.getString(DBSchema.QUESTIONS.QUESTION);
            int rightAnswer = resultSet.getInt(DBSchema.QUESTIONS.RIGHT_ANSWER);
            String language = resultSet.getString(DBSchema.QUESTIONS.LANGUAGE);

            ArrayList<Answer> answers = new ArrayList<>(answerDAO.findAllAnswersInQuestion(language, questionId));

            Question question = new Question(textQuestion, rightAnswer, language, answers);
            question.setQuestionId(questionId);
            result.add(question);
        }
        resultSet.close();
        LOG.debug("processed new question entities");
        return result;
    }

    @Override
    public List<Question> findAllQuestionsInTest(String language, long testId) throws SQLException {
        String query = String.format("SELECT * FROM %s WHERE %s = '%s' AND %s = %d",
                getTableName(),
                DBSchema.QUESTIONS.LANGUAGE,
                language,
                DBSchema.QUESTIONS.TEST_ID,
                testId);
        LOG.debug("return all questions in test");
        return (List<Question>) executeQuery(query);
    }

    @Override
    public long doesQuestionAlreadyExist(String russianQuestion) throws SQLException {
        Connection connection = getConnection();
        String query = String.format("SELECT %s FROM %s WHERE %s = '%s'",
                DBSchema.QUESTIONS.NUMBER,
                DBSchema.QUESTIONS.name(),
                DBSchema.QUESTIONS.RUSSIAN_TEXT,
                russianQuestion);
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                LOG.debug("Test, does question exist, if yes - return number of question");
                return resultSet.getLong(DBSchema.QUESTIONS.NUMBER);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return -1;
    }

    @Override
    public long createNewQuestion(String question, String russianQuestion, String language, int rightAnswer, int testId) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("INSERT INTO " + DBSchema.QUESTIONS.name() +
                    "( " + DBSchema.QUESTIONS.NUMBER + ", " + DBSchema.QUESTIONS.QUESTION + ", " + DBSchema.QUESTIONS.RUSSIAN_TEXT + ", " + DBSchema.QUESTIONS.LANGUAGE +
                    ", " + DBSchema.QUESTIONS.RIGHT_ANSWER + ", " + DBSchema.QUESTIONS.TEST_ID + ")" + " VALUES(?, ?, ?, ?, ?, ?) ");
            int numberOfQuestion = (count() / 2) + 1;
            statement.setInt(1, numberOfQuestion);
            statement.setString(2, question);
            statement.setString(3, russianQuestion);
            statement.setString(4, language);
            statement.setInt(5, rightAnswer);
            statement.setInt(6, testId);

            statement.executeUpdate();
            LOG.debug("New question was created");
            return numberOfQuestion;

        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return -1;
    }

    @Override
    public boolean willItQuestionLanguageCopy(String russianQuestion, String language) throws SQLException {
        String query = String.format("SELECT %s FROM %s WHERE %s = '%s' AND %s = '%s'",
                DBSchema.QUESTIONS.NUMBER,
                DBSchema.QUESTIONS.name(),
                DBSchema.QUESTIONS.RUSSIAN_TEXT,
                russianQuestion,
                DBSchema.QUESTIONS.LANGUAGE,
                language);
        LOG.debug("Test, will it be question copy");
        return executeQueryWillItBeEntityCopy(query);
    }

    @Override
    public void createQuestionTranslation(long numberOfQuestion, String question, String russianQuestion, String language, int rightAnswer, int testId) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("INSERT INTO " + DBSchema.QUESTIONS.name() +
                    "( " + DBSchema.QUESTIONS.NUMBER + ", " + DBSchema.QUESTIONS.QUESTION + ", " + DBSchema.QUESTIONS.RUSSIAN_TEXT + ", " + DBSchema.QUESTIONS.LANGUAGE +
                    ", " + DBSchema.QUESTIONS.RIGHT_ANSWER + ", " + DBSchema.QUESTIONS.TEST_ID + ")" + " VALUES(?, ?, ?, ?, ?, ?) ");
            statement.setLong(1, numberOfQuestion);
            statement.setString(2, question);
            statement.setString(3, russianQuestion);
            statement.setString(4, language);
            statement.setInt(5, rightAnswer);
            statement.setInt(6, testId);
            statement.executeUpdate();
            LOG.debug("Test translation was created");
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    @Override
    public long findIdByQuestionName(String question) throws SQLException {
        Connection connection = getConnection();
        String query = String.format("SELECT %s FROM %s WHERE %s = '%s'",
                DBSchema.QUESTIONS.NUMBER,
                DBSchema.QUESTIONS.name(),
                DBSchema.QUESTIONS.QUESTION,
                question);
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                LOG.debug("return question id by questionName");
                return resultSet.getLong(DBSchema.QUESTIONS.NUMBER);
            } else {
                return -1;
            }
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
        return -1;
    }

    @Override
    public List<String> findAllQuestionNames(String language) throws SQLException {
        List<String> questionNames = new ArrayList<>();
        Connection connection = getConnection();
        String query = String.format("SELECT DISTINCT %s FROM %s WHERE %s = '%s'",
                DBSchema.QUESTIONS.QUESTION,
                getTableName(),
                DBSchema.QUESTIONS.LANGUAGE,
                language);
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                LOG.debug("return all question names");
                questionNames.add(resultSet.getString(DBSchema.QUESTIONS.QUESTION));
            }
            return questionNames;
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
    protected String getTableName() {
        return DBSchema.QUESTIONS.name();
    }


}
