package com.company;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by yulia on 02.10.16.
 */
public class UserConnection implements ConsoleManager{
    static String[] arrUserInput = {"", "", ""};
    ConsoleReader consoleReader;
    DatabaseManager databaseManager = new DatabaseManager();
        UserConnection(ConsoleReader consoleReader){
            this.consoleReader = consoleReader;

        }

//    String[] wrightInputData = {getNameDatabase(), getUserName(), getPassword()};
    // TODO multinames
//    private String getDatabaseName(Connection connection) {
//        try {
//            PreparedStatement ps = connection.prepareStatement("SELECT datname FROM pg_database WHERE datistemplate = false;");
//            ResultSet rs = ps.executeQuery();
//            int i = 0;
//            while (rs.next()) {
//                i++;
//            }
//            String[] databaseNames = new String[i];
//            while (rs.next()) {
//
//                System.out.println(rs.getString(1));
//            }
//            rs.close();
//            ps.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
    @Override
    public String read() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return input;
    }
    public void getConnectionData() {
        try {
            String string = read();
            String[] data = string.split("\\|");
            if (data.length != 3) {
                throw new IllegalArgumentException("Wrong parameters number. Your number is " + data.length + " But must be 3");
            }
            String databaseName = data[0];
            String userName = data[1];
            String password = data[2];

            databaseManager.connect(databaseName, userName, password);
          //  break;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

//    public boolean compareDataConnection(String database, String user, String password, Connection connection) throws SQLException {
//        String[]  wrightInputData = { "sqlcmd", getUserName(connection), getPassword()};
//        DatabaseManager databaseManager = new DatabaseManager();
//        try {
//            databaseManager.connect(database, user, password);
//            String wrongStatement = "";
//
//            for (int i = 0; i < arrUserInput.length; i++) {
//                if (!arrUserInput[i].equals(wrightInputData[i])) {
//                    switch (i) {
//                        case 0:
//                            wrongStatement = "name of database";
//                            break;
//                        case 1:
//                            wrongStatement = "username";
//                            break;
//                        case 2:
//                            wrongStatement = "password";
//                            break;
//                    }
//                    System.out.println("Sorry, but you entered incorrect data in " + wrongStatement + ". Please, try again enter " + wrongStatement);
//                    Scanner sc = new Scanner(System.in);
//                    arrUserInput[i] = sc.next();
//                    compareDataConnection(database, user, password, connection);
//                }
//            }
//        }
//        catch (ClassNotFoundException|SQLException e){
//            System.out.println("Found exception in program");
//        }
//        return true;
//    }

    public boolean welcomeFromDatabase() {
        System.out.println("Hi, I'm Database manager! Please, write name of database in which you want to work, username and password in format: nameOfDataBase|username|password ");
        getConnectionData();
        return true;
    }
}
