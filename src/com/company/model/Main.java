package com.company.model;

import com.company.Controller.Introduction;

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
        Introduction introduction = new Introduction();
        try {
            introduction.doCommand();
        }
        catch (SQLException | ClassNotFoundException e){
            System.out.println(e);
        }
        return true;
    }
}
