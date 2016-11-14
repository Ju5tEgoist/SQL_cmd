package com.company.Command;

import com.company.*;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by yulia on 06.11.16.
 */
public class Connect implements Command {
    ConsoleReader consoleReader;
    DatabaseManager databaseManager;
    ContentsOfTheTables contentsOfTheTables;

   Connect(){
        this.consoleReader = new ConsoleReader();
        this.databaseManager = new DatabaseManager();
        this.contentsOfTheTables = new ContentsOfTheTables();
    }

    @Override
    public boolean shouldProcess(String command) {
        return command.equals("connect");
    }

    @Override
    public void process(String command) throws SQLException, ClassNotFoundException {
        boolean result = false;


        System.out.println("Please, write name of database in which you want to work, username and password in format: nameOfDataBase|username|password ");
        while (!result) {
            try {
                Connection connection = DatabaseManager.getConnection();
                result = connection != null;
            } catch (Exception e) {
                System.out.println(e.toString());
                System.out.println("Check your entries and try again");
            }
        }

    }

}