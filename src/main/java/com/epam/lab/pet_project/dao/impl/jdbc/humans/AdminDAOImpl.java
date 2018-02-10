package com.epam.lab.pet_project.dao.impl.jdbc.humans;

import com.epam.lab.pet_project.dao.interfaces.human.GlobalHumanDAO;
import com.epam.lab.pet_project.dao.impl.jdbc.AbstractDAOImpl;
import com.epam.lab.pet_project.dao.impl.jdbc.DBSchema;
import com.epam.lab.pet_project.entities.humans.Admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AdminDAOImpl extends AbstractDAOImpl implements GlobalHumanDAO {
    private static final Logger LOG = LoggerFactory.getLogger(AdminDAOImpl.class);

    public AdminDAOImpl() {
    }

    public AdminDAOImpl(Connection connection) {
        setConnection(connection);
    }

    @Override
    public String findRoleById(long id) {
        return null;
    }

    @Override
    public Admin findById(long id) throws SQLException {
        Connection connection = getConnection();
        String query = String.format("SELECT * FROM %s WHERE %s = %d",
                DBSchema.USERS.name(),
                DBSchema.USERS.ID,
                id);
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            List<Admin> admins = processResultSet(resultSet);
            if (admins.isEmpty()) {
                return null;
            }
            LOG.debug("AdminDAO findById");
            return admins.get(0);

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
        return null;
    }

    @Override
    protected List<Admin> processResultSet(ResultSet resultSet) throws SQLException {
        List<Admin> result = new ArrayList<>();
        while (resultSet.next()) {
            long adminId = resultSet.getLong(DBSchema.USERS.ID);
            String adminName = resultSet.getString(DBSchema.USERS.USER_NAME);
            String password = resultSet.getString(DBSchema.USERS.PASSWORD);
            Admin admin = new Admin(adminName, password);
            admin.setAdminId(adminId);
            result.add(admin);
        }
        resultSet.close();
        LOG.debug("new Admin has been created");
        return result;
    }

    @Override
    protected String getTableName() {
        LOG.debug("getTableName was called");
        return DBSchema.USERS.name();
    }
}
