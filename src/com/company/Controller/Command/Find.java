package com.company.Controller.Command;

import com.company.Controller.Command.Command;
import com.company.Controller.Command.List;
import com.company.Controller.ContentsOfTheTables;
import com.company.view.ConsoleReader;
import com.company.model.DatabaseList;
import java.sql.SQLException;

/**
 * Created by yulia on 06.11.16.
 */
public class Find implements Command {
    ContentsOfTheTables contentsOfTheTables = new ContentsOfTheTables();
    List list = new List();
    DatabaseList databaseList = new DatabaseList();


    public static String selectedTableName;
    @Override
    public boolean shouldProcess(String command) {
        return command != null && command.startsWith("find");
    }

    @Override
    public void process(String database) throws SQLException {
        System.out.println("For view table, please, enter the name: find <tableName> or find <tableName LIMIT/OFFSET>");
        String[] tableNames = list.getTableNames(database);
        ConsoleReader consoleReader = new ConsoleReader();
        String result = consoleReader.read();
        String[] parts = result.split(" ") ;

        selectedTableName = getSelectedTableName(tableNames, result, parts);
    }

    public String getSelectedTableName(String[] tableNames, String result, String[] parts) throws SQLException {
        for (String tableName : tableNames) {
            String expectedFirstCase = "find" + " " + tableName;
            if (result.equals(expectedFirstCase)) {
                selectedTableName = tableName;
                break;
            }

        }
        if(selectedTableName == null){
            selectedTableName = contentsOfTheTables.getLimitOffset(tableNames, result, parts);
            if(selectedTableName == null){
                System.out.println("Can not find table with this name. Try again");
            }
            else {
                databaseList.getTableForView(selectedTableName);
            }
        }
        else {
            databaseList.getTableForView(selectedTableName);
        }
        return selectedTableName;
    }

}