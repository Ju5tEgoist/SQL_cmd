package com.company;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.sql.*;
import java.util.Scanner;

/**
 * Created by yulia on 04.10.16.
 */
public class ContentsOfTheTables implements ConsoleManager{
    ConsoleReader consoleReader = new ConsoleReader();
    DatabaseList dl = new DatabaseList();


    public void getTableForView(String nameOfSelectedDatabase, Connection connection, String database) throws SQLException {
//        ResultSet list = connection.getMetaData().getTables(database, "public", nameOfSelectedDatabase, null);
//
        PreparedStatement ps = connection.prepareStatement("select * from public." + getNameOfSelectedDatabase(database, connection, dl.viewAllUsersTables(database, connection)));
//
        ResultSet rs = ps.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
//
//        System.out.println(rs.getRow());
        //return rsmd.;
        int columns = rsmd.getColumnCount();
        for (int i = 1; i <= columns; i++) {
            int jdbcType = rsmd.getColumnType(i);
            String name = rsmd.getColumnTypeName(i);
            System.out.print("Column " + i + " is JDBC type " + jdbcType);
            System.out.println(", which the DBMS calls " + name);


        }
    }

   // DatabaseList databaseList = new DatabaseList();
   @Override
   public String read() {
       Scanner sc = new Scanner(System.in);
       String input = sc.nextLine();
       return input;
   }

    public String getNameOfSelectedDatabase(String database, Connection connection, String[] strArr) throws SQLException {
        String nameOfSelectedDatabase = "";
        System.out.println("For view table, please, enter the name: find <tableName>");
        String result = read();
        for (String aStrArr : strArr) {
            String expected = "find" + " " + aStrArr;
            if (result.equals(expected)) {
                nameOfSelectedDatabase = aStrArr;
              break;
            }
        }
         if(nameOfSelectedDatabase.equals("")){
            System.out.println("Can not find table with this name. Try again");
            getNameOfSelectedDatabase(database, connection, strArr);
        }
        System.out.println(nameOfSelectedDatabase);
        return nameOfSelectedDatabase;
    }



    // fix it, because this realization depends on specific database
    public static void getTableForViewUser(ResultSet rs) throws SQLException {
        System.out.println( "name  " + "password  " + "id");
        while ( rs.next() ) {
            String  name = rs.getString("name");
            String  password = rs.getString("password");
            int id = rs.getInt("id");
            System.out.println( name + "  " + password + "  " + id + "\n"
                    + "--------------" );

        }
    }
    // fix it, because this realization depends on specific database
    public static void getTableForViewEmployee(ResultSet rs) throws SQLException {
        while ( rs.next() ) {
            String  name = rs.getString("name");
            String  password = rs.getString("position");

            System.out.println( "name = " + name + "\n" + "password = " + password + "\n" + "--------------" );

        }
    }
}
