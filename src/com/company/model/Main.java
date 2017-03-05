package com.company.model;

import com.company.controller.MainCommand;
import com.company.model.exception.CommandExecutionException;

import java.sql.SQLException;

/**
 * Created by yulia on 29.10.16.
 */
public class Main {

    public static void main(String[] args)  {
        System.out.println("Hi, I'm Database manager! ");
        MainCommand mainCommand = new MainCommand();
        try {
            mainCommand.perform();
        }
        catch (CommandExecutionException | SQLException e){
            System.out.println(e);
        }
    }


}
