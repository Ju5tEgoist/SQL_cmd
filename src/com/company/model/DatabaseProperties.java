package com.company.model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yulia on 02.10.16.
 */
public class DatabaseProperties {

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
