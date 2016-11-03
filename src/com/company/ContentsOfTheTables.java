package com.company;

import java.sql.*;

/**
 * Created by yulia on 04.10.16.
 */
public class ContentsOfTheTables{

    private int limit;
    private int offset;

    public String getChosenTableName(String[] tableNames) throws SQLException {
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
        selectedTableName = getLimitOffset(tableNames, selectedTableName, result, parts);
         if(selectedTableName.equals("")){
            System.out.println("Can not find table with this name. Try again");
            getChosenTableName(tableNames);
        }
        System.out.println(selectedTableName);
        return selectedTableName;
    }

    private String getLimitOffset(String[] tableNames, String selectedTableName, String result, String[] parts) {
        if(parts.length == 3){
            String[] partsLO = parts[parts.length-1].split("/");
            String limitString = partsLO[0];
            String offsetString = partsLO[1];
            limit = Integer.parseInt(limitString);
            offset = Integer.parseInt(offsetString);
            for (String tableName : tableNames) {
                String expectedFirstCase = "find" + " " + tableName + " " + limitString + "/" + offsetString;
                if (result.equals(expectedFirstCase)) {
                    selectedTableName = tableName;
                    break;
                }
            }

        }
        return selectedTableName;
    }
    protected int getLimit(){
        return limit;
    }

    protected int getOffset(){
        return offset;
    }
}
