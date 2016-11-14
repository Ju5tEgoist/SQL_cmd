package com.company.Command;

import com.company.*;

import java.sql.SQLException;

/**
 * Created by yulia on 06.11.16.
 */
public class Introduction {

  //  DatabaseManager databaseManager;
    String database;
    ConsoleReader consoleReader;
    ContentsOfTheTables contentsOfTheTables;
    ChangeTable changeTable;
    Connect conn;

    public Introduction(){
   //     this.databaseManager = new DatabaseManager();
        this.consoleReader = new ConsoleReader();
        this.contentsOfTheTables = new ContentsOfTheTables();
     //   this.changeTable = new ChangeTable(connection, contentsOfTheTables);
    }


    public void doCommand() throws SQLException, ClassNotFoundException {
        System.out.println( "To view all available command, enter: command list or enter the command, which you want to do");
        getDetermineCommand();
    }


    protected void getDetermineCommand() throws SQLException, ClassNotFoundException {
        String inputCommand = consoleReader.read();
        Command[] commands = {
                new CommandList(),
                new Change(contentsOfTheTables),
                new Connect(),
             //  new Delete(statement, records),
                new Find(),
             //   new Insert(consoleReader, records, statement),
                new List(),
                new Exit()
               // new Select(),
               // new Update()
        };
        for (Command command : commands) {
            try {
                if (command.shouldProcess(inputCommand)) {
                    command.process(inputCommand);
                    break;
                }
            }
            catch (Exception e) {
//                System.out.println(e);
//                if (e instanceof com.company.Command.ExitException) {
//                    throw e;
//                }
//
//                break;
                throw e;
            }

        }
        getDetermineCommand();
    }
}
