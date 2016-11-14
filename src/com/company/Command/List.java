package com.company.Command;

import com.company.DatabaseManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yulia on 06.11.16.
 */
public class List implements Command {

    DatabaseManager databaseManager = new DatabaseManager();
    private String[] tableNames;

    @Override
    public boolean shouldProcess(String command) {
        return command.equals("list");
    }

    @Override
    public void process(String database) throws SQLException, ClassNotFoundException {
        Connection connect = databaseManager.getConnection();
        ResultSet list = connect.getMetaData().getTables(database, "public", "%", null);
        System.out.printf("List of all available tables: [");
        String concatenatedTableNames = "";

        while (list.next()) {
            String tableName = list.getString(3);
            concatenatedTableNames =  tableName  + " " + concatenatedTableNames;
            System.out.print(tableName);
            System.out.print(list.isLast() ? "]" : ", ");
        }
        tableNames = concatenatedTableNames.split(" ") ;
    }
    public String[] getTableNames(){
        return tableNames;
    }
}
