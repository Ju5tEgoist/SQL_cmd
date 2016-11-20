package com.company;

import com.company.Command.Find;

import java.sql.*;

/**
 * Created by yulia on 02.11.16.
 */
public class ChangeTable {
    ConsoleReader consoleReader = new ConsoleReader();

    public void changeData( String result, int columnNumber) throws SQLException {
        System.out.println("Put the name of the action which you want to do with data: addRow, delete, rewrite");
        String action = consoleReader.read();
        String fail = null;
        switch (action){
            case "addRow": addRow();
                break;
            case "delete": delete(columnNumber, result);
                break;
            case "rewrite": rewrite(columnNumber, result);
                break;
            default: fail = "This action does not exist";
        }

        if(fail != null){
            System.out.println(fail);
            changeData(result, columnNumber);
        }
    }

    private void delete(int columnNumber, String id) throws SQLException {
        String newData = null;
        Statement stmt = DatabaseManager.getConnection().createStatement();
        String sql = "UPDATE public." + Find.selectedTableName + " set " + getColumnName(columnNumber, stmt) + " = "
                + newData +  " WHERE id = " + id +";";
        stmt.executeUpdate(sql);
    }

    private void addRow() throws SQLException {
        Statement stmt = DatabaseManager.getConnection().createStatement();
        System.out.println("Enter new data separated by space");
        String newResult = consoleReader.read();
        String[] newValues = newResult.split(" ");
        ResultSet rs = stmt.executeQuery("SELECT * FROM public." + Find.selectedTableName);
        String columnNames = getColumnNames(rs);
        String newData = getNewData(newValues);
        String sql = "INSERT INTO public." + Find.selectedTableName + " " + columnNames + " VALUES " + newData + ";" ;
        stmt.executeUpdate(sql);
    }

    private String getNewData(String[] newValues) throws SQLException {
        String newData = "(";
        for (int i = 0; i < newValues.length; i++) {
            char c = newValues[i].charAt(0);
           if(!(c >= '0' && c <= '9')){
               newValues[i] = "'" + newValues[i] + "'";
           }
        }
        int k = 0;
        while (k < newValues.length){
            if(k+1 < newValues.length) {
                newData = newData + newValues[k] + ",";
            }
            else {
                newData = newData + newValues[k] + ")";
            }
            k++;
        }
        return newData;
    }

    private String getColumnNames(ResultSet rs) throws SQLException {
        String columnNames = "(";
        int columnsNumber = rs.getMetaData().getColumnCount();
        int i = 1;
        while (i <= columnsNumber){
            if(i < columnsNumber) {
                columnNames = columnNames + rs.getMetaData().getColumnName(i) + ",";

            }
            else {
                columnNames = columnNames + rs.getMetaData().getColumnName(i) + ")";
            }
            i++;
        }
        return columnNames;
    }

    private void rewrite(int columnNumber, String id) throws SQLException {
        System.out.println("Enter new data");
        String newData = consoleReader.read();
        Statement stmt = DatabaseManager.getConnection().createStatement();
        String sql = "UPDATE public." + Find.selectedTableName + " set " + getColumnName(columnNumber, stmt) + " = "
                + "'" + newData + "'" + " WHERE id = " + id +";";
        stmt.executeUpdate(sql);
    }
    public String getColumnName(int columnNumber, Statement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT * FROM public." + Find.selectedTableName);
        return rs.getMetaData().getColumnName(columnNumber);
    }

}
