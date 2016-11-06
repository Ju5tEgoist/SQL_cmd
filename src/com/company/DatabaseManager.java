package com.company;

import java.sql.*;

/**
 * Created by yulia on 28.09.16.
 */
public class DatabaseManager {
      Connection connection;

    public Connection connect(String database, String user, String password) throws SQLException, ClassNotFoundException {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Please add jdbc jar to project.", e);
            }
            try {
                  connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/" + database, user,
                        password);
            } catch (SQLException e) {
                throw new RuntimeException(
                        String.format("Can't get connection for model:%s user:%s",
                                database, user),
                        e);

            }
        System.out.println("Connection successful");
        return connection;
    }

}
