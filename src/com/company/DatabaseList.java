package com.company;

import java.sql.*;

/**
 * Created by yulia on 02.10.16.
 */
public class DatabaseList implements TableList{

    Connection connection;
    DatabaseList(Connection connection){
        this.connection = connection;
    }
    ContentsOfTheTables contentsOfTheTables = new ContentsOfTheTables();

    ConsoleReader consoleReader = new ConsoleReader();
    String[] tableNames;

    public String[] getAllTableNames(String database) throws SQLException {

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
    public void getTableForView(String database) throws SQLException {

        String chosenTableName = contentsOfTheTables.getChosenTableName(getAllTableNames(database));
        PreparedStatement ps = connection.prepareStatement("select * from public." + chosenTableName);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int columnCount = ps.getMetaData().getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(rs.getString(i) + "|");
            }
            System.out.print("\n");
        }
    }
}
