package com.epam.lab.pet_project.dbconnection;

public interface DatabaseManager {
    Object getDatabase();
    void disconnect();
}
