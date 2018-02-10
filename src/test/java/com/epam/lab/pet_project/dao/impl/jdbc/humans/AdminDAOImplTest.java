package com.epam.lab.pet_project.dao.impl.jdbc.humans;

import com.epam.lab.pet_project.dao.impl.jdbc.H2CreateDataBase;
import com.epam.lab.pet_project.entities.humans.Admin;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class AdminDAOImplTest {
    @Test
    public void findById() throws Exception {
        //WHEN
        H2CreateDataBase h2CreateDataBase = new H2CreateDataBase();
        Connection connection = h2CreateDataBase.createH2Connection();
        AdminDAOImpl adminDAO = new AdminDAOImpl(connection);
        //THEN
        Admin admin = adminDAO.findById(4);
        h2CreateDataBase.cleanDataBase();
        assertEquals(admin.getUserName(), "admin");
    }

    @Test
    public void getTableName() throws Exception {
        //WHEN
        AdminDAOImpl adminDAO = new AdminDAOImpl();
        //THEN
        assertEquals(adminDAO.getTableName(), "users");
    }

}