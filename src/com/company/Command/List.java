package com.company.Command;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yulia on 06.11.16.
 */
public class List implements Command {
    Connection connection;
    String database;
    List(Connection connection, String database){
        this.connection = connection;
        this.database = database;
    }

    private String[] tableNames;



    @Override
    public boolean isProcess(String command) {
        return command.equals("list");
    }

    @Override
    public void process(String command) throws SQLException {
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
    }
    public String[] getTableNames(){
        return tableNames;
    }
}
