package com.company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by yulia on 04.10.16.
 */
public class ContentsOfTheTables {

    public void inputTableName(ResultSet rs) throws SQLException {
        System.out.println("For view table, please, enter the name: find tableName");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();


        switch (input){
            case  "find employee": getTableForViewEmployee(rs);
                break;
            case "find user": getTableForViewUser(rs);
                break;
           default:
               System.out.println("Can not find table with this name. Try again");
               inputTableName(rs);
        }
    }
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
    public static void getTableForViewEmployee(ResultSet rs) throws SQLException {
        while ( rs.next() ) {
            String  name = rs.getString("name");
            String  password = rs.getString("position");

            System.out.println( "name = " + name + "\n" + "password = " + password + "\n" + "--------------" );

        }
    }
}
