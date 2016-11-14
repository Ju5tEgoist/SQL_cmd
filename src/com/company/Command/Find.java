package com.company.Command;

import com.company.ConsoleReader;
import com.company.ContentsOfTheTables;
import java.sql.Connection;

/**
 * Created by yulia on 06.11.16.
 */
public class Find implements Command {
    ContentsOfTheTables contentsOfTheTables;
   // DatabaseList databaseList;
    List list;

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
    public void process(String command) {
        String[] tableNames = list.getTableNames();
        String selectedTableName = "";
        ConsoleReader consoleReader = new ConsoleReader();
        System.out.println("\nFor view table, please, enter the name: find <tableName> or find <tableName LIMIT/OFFSET>");
        String result = consoleReader.read();
        String[] parts = result.split(" ") ;

        for (String tableName : tableNames) {
            String expectedFirstCase = "find" + " " + tableName;
            if (result.equals(expectedFirstCase)) {
                selectedTableName = tableName;
                break;
            }

        }
        selectedTableName = contentsOfTheTables.getLimitOffset(tableNames, selectedTableName, result, parts);
        if(selectedTableName.equals("")){
            System.out.println("Can not find table with this name. Try again");
            shouldProcess(command);
        }
        System.out.println(selectedTableName);
    }
}
