package com.epam.lab.pet_project.dao.impl.jdbc.humans;

import com.epam.lab.pet_project.dao.impl.jdbc.H2CreateDataBase;
import org.junit.Test;
import java.sql.Connection;

import static org.junit.Assert.*;

public class UserDAOImplTest {
    @Test
    public void findAll() throws Exception {
        //WHEN
        H2CreateDataBase h2CreateDataBase = new H2CreateDataBase();
        Connection connection = h2CreateDataBase.createH2Connection();
        UserDAOImpl userDAO = new UserDAOImpl(connection);
        //THEN
        assertEquals(userDAO.findAll().size(), 4);
        h2CreateDataBase.cleanDataBase();
    }

    @Test
    public void findIdByUserNameAndPassword() throws Exception {
        //WHEN
        H2CreateDataBase h2CreateDataBase = new H2CreateDataBase();
        Connection connection = h2CreateDataBase.createH2Connection();
        UserDAOImpl userDAO = new UserDAOImpl(connection);
        //THEN
        assertEquals(userDAO.findIdByUserNameAndPassword("user1", "Qwerty1234"), 1);
        h2CreateDataBase.cleanDataBase();
    }

    @Test
    public void findRoleById() throws Exception {
        //WHEN
        H2CreateDataBase h2CreateDataBase = new H2CreateDataBase();
        Connection connection = h2CreateDataBase.createH2Connection();
        UserDAOImpl userDAO = new UserDAOImpl(connection);
        //THEN
        assertEquals(userDAO.findRoleById(2), "USER");
        h2CreateDataBase.cleanDataBase();
    }

    @Test
    public void findById() throws Exception {
        //WHEN
        H2CreateDataBase h2CreateDataBase = new H2CreateDataBase();
        Connection connection = h2CreateDataBase.createH2Connection();
        UserDAOImpl userDAO = new UserDAOImpl(connection);
        //THEN
        assertEquals(userDAO.findById(4).getUserName(), "admin");
        h2CreateDataBase.cleanDataBase();
    }

    @Test
    public void changeLogStatus() throws Exception {
        //WHEN
        H2CreateDataBase h2CreateDataBase = new H2CreateDataBase();
        Connection connection = h2CreateDataBase.createH2Connection();
        UserDAOImpl userDAO = new UserDAOImpl(connection);
        //THEN
        assertEquals(userDAO.changeLogStatus(userDAO.findById(1), true), true);
        userDAO.changeLogStatus(userDAO.findById(1), false);
        h2CreateDataBase.cleanDataBase();
    }

    @Test
    public void changeBanStatusByUserId() throws Exception {
        //WHEN
        H2CreateDataBase h2CreateDataBase = new H2CreateDataBase();
        Connection connection = h2CreateDataBase.createH2Connection();
        UserDAOImpl userDAO = new UserDAOImpl(connection);
        //THEN
        userDAO.changeBanStatusByUserId(1,"BANNED");
        assertEquals(userDAO.findById(1).getStatus(), "BANNED");
        userDAO.changeBanStatusByUserId(1,"ACTIVE");
        h2CreateDataBase.cleanDataBase();
    }

    @Test
    public void findUserIdByUserName() throws Exception {
        //WHEN
        H2CreateDataBase h2CreateDataBase = new H2CreateDataBase();
        Connection connection = h2CreateDataBase.createH2Connection();
        UserDAOImpl userDAO = new UserDAOImpl(connection);
        //THEN
        assertEquals(userDAO.findUserIdByUserName("user3"), 3);
        h2CreateDataBase.cleanDataBase();
    }

    @Test
    public void getTableName() throws Exception {
        //WHEN
        UserDAOImpl userDAO = new UserDAOImpl();
        //THEN
        assertEquals(userDAO.getTableName(), "users");

    }

}