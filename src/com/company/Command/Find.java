package com.company.Command;

import com.company.ConsoleReader;
import com.company.ContentsOfTheTables;
import com.company.DatabaseList;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by yulia on 06.11.16.
 */
public class Find implements Command {
    ContentsOfTheTables contentsOfTheTables = new ContentsOfTheTables();
   // DatabaseList databaseList;
    List list = new List();
    String tableName;
    String concatenatedTableNames;
    DatabaseList databaseList = new DatabaseList();


//    public Find(Connection connection, String database, Connect conn) {
//        this.contentsOfTheTables = new ContentsOfTheTables();
//        this.databaseList = new DatabaseList(connection);
//        this.list = new List();
//    }

    @Override
    public boolean shouldProcess(String command) {
        return command.startsWith("find");
    }

    @Override
    public void process(String database) throws SQLException {
        System.out.println("\nFor view table, please, enter the name: find <tableName> or find <tableName LIMIT/OFFSET>");

        String[] tableNames = list.getTableNames(database);
        String selectedTableName = "";
        ConsoleReader consoleReader = new ConsoleReader();

        String result = consoleReader.read();
        String[] parts = result.split(" ") ;

        for (String tableName : tableNames) {
            String expectedFirstCase = "find" + " " + tableName;
            if (result.equals(expectedFirstCase)) {
                selectedTableName = tableName;
                break;
            }

        }
        if(selectedTableName.equals("")){
            selectedTableName = contentsOfTheTables.getLimitOffset(tableNames, selectedTableName, result, parts);
            if(selectedTableName.equals("")){
                System.out.println("Can not find table with this name. Try again");
            }
            else {
                databaseList.getTableForView(selectedTableName);
            }
        }
        else {
            databaseList.getTableForView(selectedTableName);
        }

    }

}
