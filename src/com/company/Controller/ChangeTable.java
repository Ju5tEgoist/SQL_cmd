package com.company.Controller;

import com.company.Controller.Command.Find;
import com.company.model.DatabaseManager;
import com.company.view.ConsoleReader;

import java.sql.*;

/**
 * Created by yulia on 02.11.16.
 */
public class ChangeTable {
    ConsoleReader consoleReader = new ConsoleReader();

    public String changeData( String result, int columnNumber) throws SQLException, ClassNotFoundException {
        System.out.println("Put the name of the action which you want to do with data: addRow, delete, rewrite");
        String action = consoleReader.read();
        String fail = "";
        switch (action){
            case "addRow": addRow();
                break;
            case "delete": delete(columnNumber, result);
                break;
            case "rewrite": rewrite(columnNumber, result);
                break;
            default: fail = "This action does not exist";
        }

        if(!(fail.equals("")) ){
            System.out.println(fail);
            Introduction introduction = new Introduction();
            introduction.getDetermineCommand();
        }
        return fail;
    }

    public String delete(int columnNumber, String id) throws SQLException {
        String newData = " ";
        Statement stmt = DatabaseManager.getConnection().createStatement();
        String columnName = getColumnName(columnNumber, stmt);
        String sql = "UPDATE public." + Find.selectedTableName + " set " + columnName + " = " + "'"
                + newData + "'" +  " WHERE id = " + id +";";
        stmt.executeUpdate(sql);
        return columnName;
    }

    public boolean addRow() throws SQLException {
        Statement stmt = DatabaseManager.getConnection().createStatement();
        System.out.println("Enter new data separated by space");
        String newResult = consoleReader.read();
        String[] newValues = newResult.split(" ");
        ResultSet rs = stmt.executeQuery("SELECT * FROM public." + Find.selectedTableName);
        String columnNames = getAllColumnNames(rs);
        String newData = getNewData(newValues);
        String sql = "INSERT INTO public." + Find.selectedTableName + " " + columnNames + " VALUES " + newData + ";" ;
        stmt.executeUpdate(sql);
        return true;
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

    public String getAllColumnNames(ResultSet rs) throws SQLException {
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

    public String rewrite(int columnNumber, String id) throws SQLException {
        System.out.println("Enter new data");
        String newData = consoleReader.read();
        Statement stmt = DatabaseManager.getConnection().createStatement();
        String sql = "UPDATE public." + Find.selectedTableName + " set " + getColumnName(columnNumber, stmt) + " = "
                + "'" + newData + "'" + " WHERE id = " + id +";";
        stmt.executeUpdate(sql);
        return newData;
    }
    public String getColumnName(int columnNumber, Statement stmt) {
        ResultSet rs;
        String columnName = null;
        try{
        rs = stmt.executeQuery("SELECT * FROM public." + Find.selectedTableName);

        columnName = rs.getMetaData().getColumnName(columnNumber);
        } catch (SQLException e){

        }

        return columnName;
    }

}
