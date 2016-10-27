package com.company;

import java.sql.*;
import java.util.Scanner;

/**
 * Created by yulia on 04.10.16.
 */
public class ContentsOfTheTables{
    DatabaseList dl = new DatabaseList();


    public void getTableForView( Connection connection, String database) throws SQLException {

        String chosenTableName = getChosenTableName(dl.getAllTableNames(database, connection));
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
