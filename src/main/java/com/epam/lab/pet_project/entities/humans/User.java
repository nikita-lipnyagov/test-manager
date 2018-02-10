package com.epam.lab.pet_project.entities.humans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class User extends Human {
    private static final Logger LOG = LoggerFactory.getLogger(User.class);

    long userId;
    String status;
    ArrayList<Float> marks;
    boolean isActive = false;

    public User(String userName, String password, String status, ArrayList<Float> marks) {
        setUserName(userName);
        setPassword(password);
        this.status = status;
        this.marks = marks;
        LOG.debug("new User was created");
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public ArrayList<Float> getMarks() {
        return marks;
    }

    public void setMarks(ArrayList<Float> marks) {
        this.marks = marks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

}
