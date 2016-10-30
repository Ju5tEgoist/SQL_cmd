package com.company;

import java.sql.*;

/**
 * Created by yulia on 04.10.16.
 */
public class ContentsOfTheTables{


    public String getChosenTableName(String[] tableNames) throws SQLException {
        String selectedTableName = "";
        ConsoleReader consoleReader = new ConsoleReader();
        System.out.println("\nFor view table, please, enter the name: find <tableName>");
        String result = consoleReader.read();
        for (String tableName : tableNames) {
            String expected = "find" + " " + tableName;
            if (result.equals(expected)) {
                selectedTableName = tableName;
              break;
            }
        }
         if(selectedTableName.equals("")){
            System.out.println("Can not find table with this name. Try again");
            getChosenTableName(tableNames);
        }
        System.out.println(selectedTableName);
        return selectedTableName;
    }
}
