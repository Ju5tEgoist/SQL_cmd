package com.company.model;

import com.company.view.ConsoleReader;
import java.sql.*;

/**
 * Created by yulia on 02.10.16.
 */
public class DatabasePropertiesProvider {

    public String getTableName(){
        System.out.println("Enter table name");
        ConsoleReader consoleReader = new ConsoleReader();
        return consoleReader.read();
    }

    public String getAllColumnsNames(ResultSet rs) throws SQLException {
        String columnNames = "";
        int columnsNumber = rs.getMetaData().getColumnCount();
        for (int i = 1; i <= columnsNumber; i++) {
            if (i < columnsNumber) { columnNames += rs.getMetaData().getColumnName(i) + "," + " ";}
            else { columnNames += rs.getMetaData().getColumnName(i);}
        }
        return columnNames;
    }
}
