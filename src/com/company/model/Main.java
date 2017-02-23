package com.company.model;

import com.company.Controller.MainCommand;

import java.sql.SQLException;

/**
 * Created by yulia on 29.10.16.
 */
public class Main {

    public static void main(String[] args)  {
            Main main = new Main();
            main.welcomeDatabase();
    }

    public boolean welcomeDatabase()  {
        System.out.println("Hi, I'm Database manager! ");
        MainCommand mainCommand = new MainCommand();
        try {
            mainCommand.doCommand();
        }
        catch (SQLException | ClassNotFoundException e){
            System.out.println(e);
        }
        return true;
    }
}
