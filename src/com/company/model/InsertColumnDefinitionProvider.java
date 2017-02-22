package com.company.model;

import com.company.view.ConsoleReader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yulia on 22.02.17.
 */
public class InsertColumnDefinitionProvider {
    protected List getProperties( ResultSet rs) throws SQLException {
        List<InsertColumnDefinition> insertColumnDefinitions = new ArrayList<>();

        System.out.println("All column's names in this table: " + getAllColumnsNames(rs));
        for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
            System.out.println("Now enter column's name and value: <name/value>");
            ConsoleReader consoleReader = new ConsoleReader();
            String[] inputParts = consoleReader.read().split("/");
            InsertColumnDefinition insertColumnDefinition = InsertColumnDefinition.builder()
                    .name(inputParts[0])
                    .value(inputParts[1])
                    .build();
            insertColumnDefinitions.add(insertColumnDefinition);
        }
        return insertColumnDefinitions;
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
