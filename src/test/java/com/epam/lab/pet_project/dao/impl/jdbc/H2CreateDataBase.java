package com.epam.lab.pet_project.dao.impl.jdbc;

import com.ibatis.common.jdbc.ScriptRunner;

import java.io.*;
import java.sql.*;

public class H2CreateDataBase {

    public Connection createH2Connection() throws ClassNotFoundException, SQLException, IOException {
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        ScriptRunner sr = new ScriptRunner(connection, false, false);

        Reader reader1 = new BufferedReader(new FileReader("src/test/java/resources/create_database.sql"));
        sr.runScript(reader1);

        Reader reader2 = new BufferedReader(new FileReader("src/test/java/resources/data.sql"));
        sr.runScript(reader2);

        return connection;
    }

    public void cleanDataBase() throws SQLException, IOException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");

        ScriptRunner sr = new ScriptRunner(connection, false, false);
        Reader reader = new BufferedReader(new FileReader("src/test/java/resources/clean.sql"));

        sr.runScript(reader);
    }

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
//        new H2CreateDataBase().createH2Connection();
       new H2CreateDataBase().cleanDataBase();
    }
}
