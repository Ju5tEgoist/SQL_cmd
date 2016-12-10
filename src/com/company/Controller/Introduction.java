package com.company.Controller;

import com.company.Controller.Command.*;
import com.company.view.ConsoleReader;

import java.sql.SQLException;

/**
 * Created by yulia on 06.11.16.
 */
public class Introduction {

    ConsoleReader consoleReader = new ConsoleReader();

    public boolean doCommand() throws SQLException, ClassNotFoundException {
        System.out.println( "To view all available command, enter: command list or enter the command, which you want to do");
        getDetermineCommand();
        return true;
    }

    public void getDetermineCommand() throws SQLException, ClassNotFoundException {
        String inputCommand = consoleReader.read();

        Command[] commands = {
                new CommandList(),
                new Change(),
                new Connect(),
                new Find(),
                new List(),
                new Exit()
        };
        for (Command command : commands) {
            if (command.shouldProcess(inputCommand))
            {
                command.process(inputCommand);

                break;
            }
        }
            getDetermineCommand();

    }
}
