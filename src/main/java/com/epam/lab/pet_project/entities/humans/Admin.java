package com.epam.lab.pet_project.entities.humans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Admin extends Human {
    private static final Logger LOG = LoggerFactory.getLogger(Admin.class);

    long adminId;

    public Admin(String userName, String password) {
        setUserName(userName);
        setPassword(password);
        LOG.debug("new Admin was created");
    }

    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }
}
