package com.company;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by yulia on 02.10.16.
 */
public class UserConnection implements ConsoleManager {

    ConsoleReader consoleReader;
    DatabaseManager databaseManager;
    ContentsOfTheTables contentsOfTheTables;

    public UserConnection(){
        this.consoleReader = new ConsoleReader();
        this.databaseManager = new DatabaseManager();
        this.contentsOfTheTables = new ContentsOfTheTables();
    }

    public boolean equalCommand() throws SQLException {
        boolean result = false;
        if(consoleReader.read().equals("list")){
            result = true;
        }
        else {
            System.out.println("Unfortunately, this command does not exist. Please, try again");
            equalCommand();
        }
        return result;
    }

    @Override
    public String read() {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        return input;
    }
}
