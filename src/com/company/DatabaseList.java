package com.company;

import java.sql.*;

/**
 * Created by yulia on 02.10.16.
 */
public class DatabaseList {
    ConsoleReader consoleReader = new ConsoleReader();
    String[] strArr;

    public String[] viewAllUsersTables(String database, Connection connection) throws SQLException {

        ResultSet list = connection.getMetaData().getTables(database, "public", "%", null);
        System.out.printf("List of all available tables: [");
        String arrOfTableNames = "";

        while (list.next()) {

            String tableName = list.getString(3);

            arrOfTableNames =  tableName  + " " + arrOfTableNames;

            System.out.print(tableName);
            if (list.isLast()){
                System.out.print("]");
            }
            else {
                System.out.print(", ");
            }
        }

        strArr = arrOfTableNames.split(" ") ;
        return strArr;
    }



//    public int getColumnsNumber(String database, Connection connection, String[] strArr) throws SQLException {
//        PreparedStatement ps = connection.prepareStatement("select * from public." + contentsOfTheTables.getNameOfSelectedDatabase(database, connection, strArr));
//        ResultSet rs = ps.executeQuery();
//        ResultSetMetaData rsmd = rs.getMetaData();
//        return rsmd.;
//    }

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
