package com.epam.lab.pet_project.services;

import com.epam.lab.pet_project.dao.impl.jdbc.humans.AdminDAOImpl;
import com.epam.lab.pet_project.entities.humans.Admin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class AdminService {
    private static final Logger LOG = LoggerFactory.getLogger(AdminService.class);
    private final AdminDAOImpl adminDAO = new AdminDAOImpl();

    public Admin findById(long id){
        Admin admin = null;
        try {
            admin = adminDAO.findById(id);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
        return admin;
    }

}
