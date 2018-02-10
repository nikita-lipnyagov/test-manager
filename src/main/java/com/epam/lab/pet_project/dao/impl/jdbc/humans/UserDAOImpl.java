package com.epam.lab.pet_project.dao.impl.jdbc.humans;

import com.epam.lab.pet_project.dao.interfaces.human.UserDAO;
import com.epam.lab.pet_project.dao.impl.jdbc.AbstractDAOImpl;
import com.epam.lab.pet_project.dao.impl.jdbc.DBSchema;
import com.epam.lab.pet_project.entities.humans.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends AbstractDAOImpl implements UserDAO {
    private static final Logger LOG = LoggerFactory.getLogger(UserDAOImpl.class);

    public UserDAOImpl() {
    }

    public UserDAOImpl(Connection connection) {
        setConnection(connection);
    }

    private boolean executeUpdateQuery(String query) throws SQLException {
        Connection connection = getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            LOG.debug("Statement was successfully executed at executeUpdateQuery method");
            return true;

        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return false;
    }


    @Override
    public List<User> findAll() throws SQLException {
        String query = "SELECT * FROM " + getTableName();
        LOG.debug("All users returned");
        return (List<User>) executeQuery(query);
    }

    @Override
    public long findIdByUserNameAndPassword(String userName, String password) throws SQLException {
        Connection connection = getConnection();
        String query = String.format("SELECT %s FROM %s WHERE %s = '%s' AND %s = '%s'",
                DBSchema.USERS.ID,
                DBSchema.USERS.name(),
                DBSchema.USERS.USER_NAME,
                userName,
                DBSchema.USERS.PASSWORD,
                password);
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                LOG.debug("return user id method findIdByUserNameAndPassword");
                return resultSet.getLong(DBSchema.USERS.ID);
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
    public String findRoleById(long id) throws SQLException {
        Connection connection = getConnection();
        String query = String.format("SELECT %s FROM %s WHERE %s = %d",
                DBSchema.USERS.ROLE,
                DBSchema.USERS.name(),
                DBSchema.USERS.ID,
                id);
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                LOG.debug("user role returned");
                return resultSet.getString(DBSchema.USERS.ROLE);
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
        return null;
    }

    @Override
    public User findById(long id) throws SQLException {
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
            List<User> users = processResultSet(resultSet);
            if (users.isEmpty()) {
                return null;
            }
            LOG.debug("User was found by id");
            return users.get(0);

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
    protected List<User> processResultSet(ResultSet resultSet) throws SQLException {
        List<User> result = new ArrayList<>();
        while (resultSet.next()) {
            long userId = resultSet.getLong(DBSchema.USERS.ID);
            String userName = resultSet.getString(DBSchema.USERS.USER_NAME);
            String password = resultSet.getString(DBSchema.USERS.PASSWORD);
            String status = resultSet.getString(DBSchema.USERS.STATUS);
            boolean isActive = resultSet.getBoolean(DBSchema.USERS.IS_ACTIVE);
            ArrayList<Float> marks = new ArrayList<>();
            int columnNumber = resultSet.getMetaData().getColumnCount();
            final int INDEX_OF_COLUMN_FROM_WHERE_START_MARKS = 7;
            for (int i = INDEX_OF_COLUMN_FROM_WHERE_START_MARKS; i <= columnNumber; i++) {
                marks.add(resultSet.getFloat(i));
            }
            User user = new User(userName, password, status, marks);
            user.setUserId(userId);
            user.setActive(isActive);
            result.add(user);
        }
        resultSet.close();
        LOG.debug("processed new users");
        return result;
    }

    @Override
    public boolean writeTestMark(long idTest, Double mark, User user) throws SQLException {
        String markTest = DBSchema.USERS.MARK_TEST + idTest;
        String query = String.format("UPDATE %s SET %s = " + mark + " WHERE %s = %d",
                DBSchema.USERS.name(),
                markTest,
                DBSchema.USERS.ID,
                user.getUserId());
        return executeUpdateQuery(query);
    }

    @Override
    public boolean changeLogStatus(User user, boolean isToTheSystem) throws SQLException {
        Connection connection = getConnection();
        String query = String.format("UPDATE %s SET %s = %s WHERE %s = %d",
                DBSchema.USERS.name(),
                DBSchema.USERS.IS_ACTIVE,
                isToTheSystem,
                DBSchema.USERS.ID,
                user.getUserId());
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            user.setActive(isToTheSystem);
            return true;

        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }finally {
            if (statement != null) {
                statement.close();
            }
        }
        return false;
    }

    @Override
    public List<String> findAllUserNamesWithStatus(String status) throws SQLException {
        Connection connection = getConnection();
        List<String> userNames = new ArrayList<>();
        String query = String.format("SELECT %s FROM %s WHERE %s = 'USER' AND %s = '%s'",
                DBSchema.USERS.USER_NAME,
                getTableName(),
                DBSchema.USERS.ROLE,
                DBSchema.USERS.STATUS,
                status);
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                LOG.debug("returned all userNames with " + status + "status");
                userNames.add(resultSet.getString(DBSchema.USERS.USER_NAME));
            }
            return userNames;
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
    public void changeBanStatusByUserId(long userId, String status) throws SQLException {
        Connection connection = getConnection();
        String query = String.format("UPDATE %s SET %s = '%s' WHERE %s = %d",
                DBSchema.USERS.name(),
                DBSchema.USERS.STATUS,
                status,
                DBSchema.USERS.ID,
                userId);
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            LOG.debug("ban status was changed");
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
    public long findUserIdByUserName(String userName) throws SQLException {
        Connection connection = getConnection();
        String query = String.format("SELECT %s FROM %s WHERE %s = '%s'",
                DBSchema.USERS.ID,
                DBSchema.USERS.name(),
                DBSchema.USERS.USER_NAME,
                userName);
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            resultSet.next();
            LOG.debug("Returns user id by userName");
            return resultSet.getLong(DBSchema.USERS.ID);
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
    public boolean addTestColumn(long numberOfTest) throws SQLException {
        String column = DBSchema.USERS.MARK_TEST + numberOfTest;
        String query = String.format("ALTER TABLE %s ADD %s DECIMAL UNSIGNED DEFAULT 0",
                DBSchema.USERS.name(),
                column);
        LOG.debug("New test column");
        return executeUpdateQuery(query);
    }

    @Override
    protected String getTableName() {
        return DBSchema.USERS.name();
    }
}
