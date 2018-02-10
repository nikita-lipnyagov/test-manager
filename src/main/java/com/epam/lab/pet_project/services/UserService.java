package com.epam.lab.pet_project.services;

import com.epam.lab.pet_project.dao.interfaces.human.UserDAO;
import com.epam.lab.pet_project.dao.impl.jdbc.humans.UserDAOImpl;
import com.epam.lab.pet_project.entities.humans.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    private final UserDAO userDAO = new UserDAOImpl();

    public String checkUserName(String userName) {
        String userNamePattern = "^[a-zA-Z](.[a-zA-Z0-9_-]*)$";
        if(userName.matches(userNamePattern)){
            LOG.debug("userName corresponds to rules");
            return "success";
        }
        return "error";
    }

    public String checkUserPassword(String password) {
        String passwordPattern = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";
        if(password.matches(passwordPattern)){
            LOG.debug("password corresponds to rules");
            return "success";
        }
        return "error";
    }

    public long findIdByUserNameAndPassword(String userName, String password) {
        long id = -1;
        try {
            id = userDAO.findIdByUserNameAndPassword(userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public String findRoleById(long id){
        try {
            return userDAO.findRoleById(id);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public User findById(long id){
        try {
            return (User) userDAO.findById(id);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public boolean writeTestMark(long idTest, Double mark, User user) {
        try {
            return  userDAO.writeTestMark(idTest, mark, user);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean changeLogStatus(User user, boolean isToTheSystem) {
        try {
            return userDAO.changeLogStatus(user, isToTheSystem);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public List<String> findAllUserNamesWithStatus(String status){
        try {
            return userDAO.findAllUserNamesWithStatus(status);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void changeBanStatusByUserId(long userId, String status) {
        try {
            userDAO.changeBanStatusByUserId(userId, status);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public long findUserIdByUserName(String userName){
        try {
            return userDAO.findUserIdByUserName(userName);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }

    public boolean addTestColumn(long numberOfTest) {
        try {
            return userDAO.addTestColumn(numberOfTest);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}
