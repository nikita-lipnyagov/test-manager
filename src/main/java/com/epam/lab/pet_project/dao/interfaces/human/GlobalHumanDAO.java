package com.epam.lab.pet_project.dao.interfaces.human;

import java.sql.SQLException;

public interface GlobalHumanDAO<T> {
    String findRoleById(long id) throws SQLException;

    T findById(long id) throws SQLException;
}
