package com.epam.lab.pet_project.dao.impl.jdbc.tests;

import com.epam.lab.pet_project.dao.interfaces.test.AnswerDAO;
import com.epam.lab.pet_project.dao.impl.jdbc.DBSchema;
import com.epam.lab.pet_project.entities.tests.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnswerDAOImpl extends AbstractTestDAOImpl implements AnswerDAO {
    private static final Logger LOG = LoggerFactory.getLogger(AnswerDAOImpl.class);

    public AnswerDAOImpl() {
    }

    public AnswerDAOImpl(Connection connection) {
        setConnection(connection);
    }

    @Override
    public List<Answer> findAll(String language) throws SQLException {
        LOG.debug("return all answers");
        return (List<Answer>) super.findAll(language);
    }

    @Override
    protected List<Answer> processResultSet(ResultSet resultSet) throws SQLException {
        List<Answer> result = new ArrayList<>();
        while (resultSet.next()) {
            long answerId = resultSet.getLong(DBSchema.ANSWERS.NUMBER);
            String textAnswer = resultSet.getString(DBSchema.ANSWERS.ANSWER);
            String language = resultSet.getString(DBSchema.ANSWERS.LANGUAGE);
            Answer answer = new Answer(textAnswer, language);
            answer.setAnswerId(answerId);
            result.add(answer);
        }
        resultSet.close();
        LOG.debug("process new answer entities");
        return result;
    }

    @Override
    public List<Answer> findAllAnswersInQuestion(String language, long questionId) throws SQLException {
        String query = String.format("SELECT * FROM %s WHERE %s = '%s' AND %s = %d",
                getTableName(),
                DBSchema.QUESTIONS.LANGUAGE,
                language,
                DBSchema.ANSWERS.QUESTION_ID,
                questionId);
        LOG.debug("return all answers in question");
        return (List<Answer>) executeQuery(query);
    }

    @Override
    public void createNewAnswer(String answerText, long questionId, String language) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
             statement = connection.prepareStatement("INSERT INTO " + DBSchema.ANSWERS.name() +
                    "( " + DBSchema.ANSWERS.NUMBER + ", "
                    + DBSchema.ANSWERS.ANSWER + ", "
                    + DBSchema.ANSWERS.LANGUAGE + ", "
                    + DBSchema.ANSWERS.QUESTION_ID + ")"
                    + " VALUES(?, ?, ?, ?) ");
            int numberOfAnswer = (count() / 2) + 1;

            statement.setLong(1, numberOfAnswer);
            statement.setString(2, answerText);
            statement.setString(3, language);
            statement.setLong(4, questionId);

            statement.executeUpdate();
            LOG.debug("new answer was created");
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
    protected String getTableName() {
        return DBSchema.ANSWERS.name();
    }
}
