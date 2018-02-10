package com.epam.lab.pet_project.dao.impl.jdbc.tests;

import com.epam.lab.pet_project.dao.impl.jdbc.AbstractDAOImpl;
import com.epam.lab.pet_project.dao.impl.jdbc.DBSchema;
import com.epam.lab.pet_project.dbconnection.DatabaseManager;
import com.epam.lab.pet_project.dbconnection.impl.jdbc.JDBCDatabaseManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class AbstractTestDAOImpl extends AbstractDAOImpl {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractTestDAOImpl.class);

    public List<?> findAll(String language) throws SQLException {
        String query  = String.format("SELECT * FROM %s WHERE language = '%s'",
                getTableName(),
                language);
        return executeQuery(query);
    }

    boolean executeQueryWillItBeEntityCopy(String query) throws SQLException {
        Connection connection = getConnection();
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                LOG.debug("execute query and test will it be entity copy");
                return true;
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
        return false;
    }

    long executeQueryReturnId(String query) throws SQLException {
        Connection connection = getConnection();
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                LOG.debug("execute query and return id");
                return resultSet.getLong(DBSchema.TESTS.NUMBER);
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
}
