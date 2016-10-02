package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by yulia on 02.10.16.
 */
public class DatabaseList {


    public static void viewAllUsersTables(String database, Connection connection) throws SQLException {

        ResultSet list = connection.getMetaData().getTables(database, "public", "%", null);
        System.out.printf("List of all available tables: [");

        while (list.next()) {
            String tableName = list.getString(3);
            System.out.print(tableName);
            if(list.isLast()){
                System.out.print("]");
            }
            else {
                System.out.print(", ");
            }
        }
    }

    public static void equalCommand(String database, Connection connection) throws SQLException {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        if(input.equals("list")){
            viewAllUsersTables(database, connection);
        }
        else {
            System.out.println("Unfortunately, this command does not exist. Please, try again");
            equalCommand(database,connection);
        }
    }
}
