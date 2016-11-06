package com.company.Command;

import com.company.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by yulia on 06.11.16.
 */
public class CommandList {
    Connection connection;
    DatabaseManager databaseManager;
    String database;
    String user;
    String password;
    UserConnection userConnection;
    ConsoleReader consoleReader;
    ContentsOfTheTables contentsOfTheTables;
    String[] tableNames;
    ChangeTable changeTable;
    String chosenTableName;
    String records;
    Statement statement;

    public CommandList(Connection connection,  String database, String user, String password, ContentsOfTheTables contentsOfTheTables, String[] tableNames, String chosenTableName, String records, Statement statement){
        this.connection = connection;
        this.databaseManager = new DatabaseManager();
        this.database = database;
        this.user = user;
        this.password = password;
        this.consoleReader = new ConsoleReader();
        this.contentsOfTheTables = new ContentsOfTheTables();
        this.userConnection = new UserConnection();
        this.tableNames = tableNames;
        this.changeTable = new ChangeTable(connection, contentsOfTheTables);
        this.chosenTableName = chosenTableName;
        this.records = records;
        this.statement = statement;
    }

    public static final String INFO = "All available command: " +
            "\nconnect - connect to database" +
            "\nlist - to review all user's tables" +
            "\nfind <tableName> - to find and view table in database" +
            "\nfind <tableName Limit/Offset> - to find and view part of table in database" +
            "\nchange <columnNumber|rowNumber> - to change data in the table" +
            "\ninsert - to add new rows of data to a table in the database" +
            "\nselect - to select data from the database" +
            "\ndelete - to delete records in the table" +
            "\nupdate - to update records in a table" +
            "\nexit";

    public void doCommand() throws SQLException, ClassNotFoundException {
        System.out.println( "To view all available command, enter: command list");
        ConsoleReader consoleReader = new ConsoleReader();
        String inputData = consoleReader.read();
        if(!inputData.equals("command list")){
            System.out.println("This command does not exist. Try again");
            doCommand();
        }
        else {
            System.out.println(INFO);
            getDetermineCommand();

        }
    }

    private void getDetermineCommand() throws SQLException, ClassNotFoundException {
        String inputCommand = consoleReader.read();

        Command[] commands = {
                new Change(connection, contentsOfTheTables),
                new Connect(),
             //  new Delete(statement, records),
                new Find(chosenTableName, database),
             //   new Insert(consoleReader, records, statement),
                new List(connection, database),
                new Exit()
               // new Select(),
               // new Update()
        };
        for (Command command : commands) {
            try {
                if (command.isProcess(inputCommand)) {
                    command.process(inputCommand);
                    break;
                }
            } catch (Exception e) {
                if (e instanceof com.company.Command.ExitException) {
                    throw e;
                }
                System.out.println(e);
                break;
            }
        }

    }
}
