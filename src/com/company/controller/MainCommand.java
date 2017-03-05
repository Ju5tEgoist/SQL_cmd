package com.company.controller;

import com.company.controller.command.*;
import com.company.model.exception.CommandExecutionException;
import com.company.view.ScannerConsoleReader;

import java.sql.SQLException;

/**
 * Created by yulia on 06.11.16.
 */
public class MainCommand {

    public void perform() throws CommandExecutionException, SQLException {
        System.out.println( "Enter the command, which you want to do or 'help'");
        getCommand();
    }

    public void getCommand() throws CommandExecutionException, SQLException {
        ScannerConsoleReader scannerConsoleReader = new ScannerConsoleReader();
        String inputCommand = scannerConsoleReader.read();
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
            System.out.println("Invalid command! Please try again.");
        }
        System.out.println( "Enter the command, which you want to do or 'help'");
            getCommand();
    }
}
