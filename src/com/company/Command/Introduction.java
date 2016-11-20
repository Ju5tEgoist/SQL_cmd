package com.company.Command;

import com.company.*;

import java.sql.SQLException;

/**
 * Created by yulia on 06.11.16.
 */
public class Introduction {

    ConsoleReader consoleReader;
    ContentsOfTheTables contentsOfTheTables;

    public Introduction(){
        this.consoleReader = new ConsoleReader();
        this.contentsOfTheTables = new ContentsOfTheTables();
    }

    public void doCommand() throws SQLException, ClassNotFoundException {
        System.out.println( "To view all available command, enter: command list or enter the command, which you want to do");
        getDetermineCommand();
    }

    protected void getDetermineCommand() throws SQLException, ClassNotFoundException {
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
            try {
                if (command.shouldProcess(inputCommand)) {
                    command.process(inputCommand);
                    break;
                }
            }
            catch (Exception e) {
                throw e;
            }

        }
        getDetermineCommand();
    }
}
