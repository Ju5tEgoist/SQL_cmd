package com.company.Controller;

import com.company.Controller.Command.*;
import com.company.view.ConsoleReader;

import java.sql.SQLException;

/**
 * Created by yulia on 06.11.16.
 */
public class MainCommand {



    public boolean doCommand() throws SQLException, ClassNotFoundException {
        System.out.println( "Enter the command, which you want to do or 'help'");
        getDetermineCommand();
        return true;
    }

    public void getDetermineCommand() throws SQLException, ClassNotFoundException {
        ConsoleReader consoleReader = new ConsoleReader();
        String inputCommand = consoleReader.read();
        boolean condition = false;
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
            if (command.shouldProcess(inputCommand))
            {
                condition = true;
                command.process(inputCommand);
                break;
            }
        }
        if(!condition){
            System.out.println("This command does not exist. Try again");
        }
        System.out.println( "Enter the command, which you want to do or 'help'");
            getDetermineCommand();

    }
}
