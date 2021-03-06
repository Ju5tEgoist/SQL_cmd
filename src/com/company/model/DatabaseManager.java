package com.company.model;

import com.company.view.ConsoleReader;

import java.sql.*;

/**
 * Created by yulia on 28.09.16.
 */
public class DatabaseManager {
    public static Connection connection;
    private static Statement stmt;

    public static Connection connect(String database, String user, String password) throws SQLException, ClassNotFoundException {

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

        stmt = connection.createStatement();
        return connection;
    }

    public Statement getStatement(){
        return stmt;
    }

    public static Connection getConnection()  {
        if(connection == null) {
            try {
                ConsoleReader consoleReader = new ConsoleReader();
                String string = consoleReader.read();
                String[] data = string.split("\\|");
                if (data.length != 3) {
                    throw new IllegalArgumentException("Wrong parameters number. Your number is " + data.length + " But must be 3");
                }
                String database = data[0];
                String userName = data[1];
                String password = data[2];
                connection = connect(database, userName, password);
            }
            catch (SQLException | ClassNotFoundException e){
                System.out.println(e);
            }

        }
        return connection;
    }
}
