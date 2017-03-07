package com.company.model;

import com.company.controller.MainController;
import com.company.model.exception.CommandExecutionException;
import com.company.view.ConsoleManager;
import com.company.view.View;

import java.sql.SQLException;

/**
 * Created by yulia on 29.10.16.
 */
public class Main {

    public static void main(String[] args)  {
        View view = new ConsoleManager();
        view.write("Hi, I'm Database manager! ");
        MainController mainController = new MainController();
        try {
            mainController.perform();
        }
        catch (CommandExecutionException | SQLException e){
            view.write(e.toString());
        }
    }


}
