package com.company.controller;

import com.company.controller.command.*;
import com.company.model.exception.CommandExecutionException;
import com.company.view.ConsoleManager;
import com.company.view.View;

import java.sql.SQLException;

/**
 * Created by yulia on 06.11.16.
 */
public class MainController {

    View view = new ConsoleManager();

    public void perform() throws CommandExecutionException, SQLException {
        view.write( "Enter the command, which you want to do or 'help'");
        getCommand();
    }

    public void getCommand() throws CommandExecutionException, SQLException {
        String inputCommand = view.read();
        boolean isCorrectCommand = false;
        Command[] commands = {
                new Help(),
                new Connect(),
                new Find(),
                new List(),
                new Exit(),
                new Clear(),
                new Drop(),
                new Create(),
                new Insert(),
                new Update(),
                new Delete()
        };
        for (Command command : commands) {
            if (command.shouldExecute(inputCommand))
            {
                isCorrectCommand = true;
                command.execute();
                break;
            }
        }
        if(!isCorrectCommand){
            view.write("Invalid command! Please try again.");
        }
        view.write( "Enter the command, which you want to do or 'help'");
            getCommand();
    }
}
