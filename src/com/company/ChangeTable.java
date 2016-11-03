package com.company;

import java.sql.*;

/**
 * Created by yulia on 02.11.16.
 */
public class ChangeTable {
    ConsoleReader consoleReader = new ConsoleReader();
    private int columnNumber;
    private int rowNumber;
    Connection connection;
    ContentsOfTheTables contentsOfTheTables;
    ChangeTable(Connection connection, ContentsOfTheTables contentsOfTheTables){
        this.connection = connection;
        this.contentsOfTheTables = contentsOfTheTables;
    }


    public void defineData(String chosenTableName) throws SQLException {
        System.out.println("To change data in table enter column's and row's numbers in format: change <columnNumber|rowNumber>");
        String resultString = consoleReader.read();
        String[] parts = resultString.split(" ");

        if (parts.length == 2) {
            String[] partsCR = parts[parts.length - 1].split("\\|");
            String limitString = partsCR[0];
            String offsetString = partsCR[1];
            columnNumber = Integer.parseInt(limitString);
            rowNumber = Integer.parseInt(offsetString);
            int offset = contentsOfTheTables.getOffset();
            if(offset != 0){
                rowNumber = rowNumber + offset;
            }
        }
        else {
            System.out.println("This command does not exist. Try again");
            defineData(chosenTableName);
        }

        Statement st = connection.createStatement();
        String result = "";
        ResultSet rs = st.executeQuery("SELECT * FROM public." + chosenTableName);
        int i = 1;
        while (rs.next()){
            if(i <= rowNumber){
                result = rs.getString(columnNumber);
                i++;
            }
        }
        System.out.println("The data which you want to change: " + result);
        changeData(result, st, chosenTableName);
    }

    public void changeData( String result, Statement st, String chosenTableName) throws SQLException {
        System.out.println("Put the name of the action which you want to do with data: insert, select, delete, update");
        String action = consoleReader.read();
        String fail = null;
        switch (action){
            case "insert": insert(st, chosenTableName, result);
                break;
            case "select": select(st, chosenTableName, result);
                break;
            case "delete": delete(st, chosenTableName, result);
                break;
            case "update": update(st, chosenTableName, result);
                break;
            default: fail = "This action does not exist";
        }

        if(fail != null){
            System.out.println(fail);
            changeData(result, st, chosenTableName);
        }

//        rs.close();
//        stmt.close();
//        connection.close();
    }

    private void update(Statement st, String chosenTableName, String result) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("UPDATE public." + chosenTableName + " VALUES " + result);
    }

    private void delete(Statement st, String chosenTableName, String result) throws SQLException {
        String  sql = "DELETE from public." + chosenTableName + " VALUES " + result ;
        st.executeUpdate(sql);

    }

    private void select(Statement st, String chosenTableName, String result) throws SQLException {
               st.executeQuery( "SELECT * FROM public." + chosenTableName + " VALUE " + result  );
    }

    private void insert(Statement st, String chosenTableName, String result) throws SQLException {
        //insert
        System.out.println("Enter new data");
        String newResult = consoleReader.read();

        String sql = "INSERT INTO public." + chosenTableName + " " + result + " VALUES " + newResult ;
        st.executeUpdate(sql);
    }
}
