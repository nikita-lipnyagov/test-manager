package com.epam.lab.pet_project.dao.impl.jdbc;

import com.epam.lab.pet_project.dbconnection.DatabaseManager;
import com.epam.lab.pet_project.dbconnection.impl.jdbc.JDBCDatabaseManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class AbstractDAOImpl {
    private DatabaseManager databaseManager = new JDBCDatabaseManager();
    private static final Logger LOG = LoggerFactory.getLogger(AbstractDAOImpl.class);
    private Connection connection;

    protected int count() throws SQLException {
        Connection connection = getConnection();
        String query = String.format("SELECT COUNT(*) FROM %s", getTableName());
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            resultSet.next();
            LOG.debug("Return count of rows in table");
            return resultSet.getInt(1);
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

    protected List<?> executeQuery(String query) throws SQLException {
        Connection connection = getConnection();
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            LOG.debug("Statement was executed");
            return processResultSet(resultSet);
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

    protected abstract List<?> processResultSet(ResultSet resultSet) throws SQLException;

    public void setConnection(Connection connection) {
       this.connection = connection;
    }

    protected Connection getConnection() {
        if(connection != null){
            return connection;
        }else{
            return (Connection) databaseManager.getDatabase();
        }
        //return (Connection) databaseManager.getDatabase();
    }

    protected abstract String getTableName();
}
