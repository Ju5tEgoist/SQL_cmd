package com.company;

import java.sql.*;

/**
 * Created by yulia on 02.10.16.
 */
public class DatabaseList {

    ConsoleReader consoleReader = new ConsoleReader();
    String[] tableNames;

    public String[] getAllTableNames(String database, Connection connection) throws SQLException {

        ResultSet list = connection.getMetaData().getTables(database, "public", "%", null);
        System.out.printf("List of all available tables: [");
        String concatenatedTableNames = "";

        while (list.next()) {
            String tableName = list.getString(3);
            concatenatedTableNames =  tableName  + " " + concatenatedTableNames;
            System.out.print(tableName);
            if (list.isLast()){
                System.out.print("]");
            }
            else {
                System.out.print(", ");
            }
        }
        tableNames = concatenatedTableNames.split(" ") ;
        return tableNames;
    }

    public boolean equalCommand(String database, Connection connection) throws SQLException {

        boolean result = false;
        if(consoleReader.read().equals("list")){
            result = true;
        }
        else {
            System.out.println("Unfortunately, this command does not exist. Please, try again");
            equalCommand(database,connection);
        }
        return result;
    }
}
