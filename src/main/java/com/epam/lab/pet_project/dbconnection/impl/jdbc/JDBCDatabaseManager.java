package com.epam.lab.pet_project.dbconnection.impl.jdbc;

import com.epam.lab.pet_project.dbconnection.DatabaseManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class JDBCDatabaseManager implements DatabaseManager {
    private static final Logger LOG = LoggerFactory.getLogger(JDBCDatabaseManager.class);
    private Connection _connection;

    @Override
    public Object getDatabase() {
        if (_connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                _connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_manager?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false&createDatabaseIfNotExist=true", "root", "root");
                LOG.debug("connection was opened");
            } catch (SQLException | ClassNotFoundException e) {
                LOG.error(e.getMessage());
                e.printStackTrace();
            }
        }

        return _connection;
    }

    @Override
    public void disconnect() {
        if (_connection != null) {
            try {
                _connection.close();
                LOG.debug("connection was closed");
            } catch (SQLException e) {
                LOG.error(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
