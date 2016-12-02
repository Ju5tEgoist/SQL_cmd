package com.company.model;

import com.company.Controller.Introduction;

/**
 * Created by yulia on 29.10.16.
 */
public class Main {

    public static void main(String[] args)  {
            Main main = new Main();
            main.welcomeDatabase();
        DatabaseManager databaseManager = new DatabaseManager();

    }

    public boolean welcomeDatabase()  {
        System.out.println("Hi, I'm Database manager! ");
        Introduction introduction = new Introduction();
        try {
            introduction.doCommand();
        }
        catch (Exception e){}
        return true;
    }
}
