package com.company;

import com.company.Command.Introduction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by yulia on 29.10.16.
 */
public class Main {

    public static void main(String[] args)  {

            Main main = new Main();
            main.welcomeDatabase();
            DatabaseManager databaseManager = new DatabaseManager();

    }

    public void welcomeDatabase()  {
        System.out.println("Hi, I'm Database manager! ");
        Introduction introduction = new Introduction();
            introduction.doCommand();
    }
}
