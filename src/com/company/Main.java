package com.company;

import java.sql.SQLException;

/**
 * Created by yulia on 29.10.16.
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Main main = new Main();
        main.welcomeDatabase();
//



    }


    public void welcomeDatabase() throws SQLException, ClassNotFoundException {
        System.out.println("Hi, I'm Database manager! ");
        System.out.println("Please, write name of database in which you want to work, username and password in format: nameOfDataBase|username|password ");
       DatabaseManager databaseManager = new DatabaseManager();
       // Connection connection = databaseManager.connect("", "", "");

        UserConnection userConnection = new UserConnection(new ConsoleReader(), databaseManager, new ContentsOfTheTables());
       // ContentsOfTheTables contentsOfTheTables = new ContentsOfTheTables();
        userConnection.getConnectionData();


    }
}
