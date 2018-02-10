package com.epam.lab.pet_project.dao.interfaces.human;

import com.epam.lab.pet_project.entities.humans.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO extends GlobalHumanDAO {
    List<User> findAll() throws SQLException;

    long findIdByUserNameAndPassword(String userName, String password) throws SQLException;

    boolean writeTestMark(long idTest, Double mark, User user) throws SQLException;

    boolean changeLogStatus(User user, boolean isToTheSystem) throws SQLException;

    List<String> findAllUserNamesWithStatus(String status) throws SQLException;

    void changeBanStatusByUserId(long userId, String status) throws SQLException;

    long findUserIdByUserName(String userName) throws SQLException;

    boolean addTestColumn(long numberOfTest) throws SQLException;
}
