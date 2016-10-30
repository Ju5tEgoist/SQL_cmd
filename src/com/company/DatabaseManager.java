package com.company;

import java.sql.*;

/**
 * Created by yulia on 28.09.16.
 */
public class DatabaseManager {
    private static ConsoleReader consoleReader;
//     String database;
      Connection connection;
//    public DatabaseManager(){}
//    public DatabaseManager(Connection connection){ this.connection = connection; }

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


    public Connection getConnection(){
    DatabaseManager databaseManager = new DatabaseManager();
      //  System.out.println(databaseManager.connection);
    return   databaseManager.connection;
    }
//    public Connection getConnection(){
//        return this.connection;
//    }
//    public String getDatabase(){
//        return this.database;
//    }



}
