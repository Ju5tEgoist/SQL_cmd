package com.company.model;

import com.company.view.ScannerConsoleReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yulia on 22.02.17.
 */
public class InsertColumnDefinitionProvider {
    public List getProperties( ResultSet rs) throws SQLException {
        List<InsertUpdateDeleteColumnDefinition> insertColumnDefinitions = new ArrayList<>();
        DatabaseProperties databaseProperties = new DatabaseProperties();
        System.out.println("All column's names in this table: " + databaseProperties.getAllColumnsNames(rs));
        for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
            System.out.println("Now enter column's name and value: <name/value>");
            ScannerConsoleReader scannerConsoleReader = new ScannerConsoleReader();
            String[] inputParts = scannerConsoleReader.read().split("/");
            InsertUpdateDeleteColumnDefinition insertColumnDefinition = InsertUpdateDeleteColumnDefinition.builder()
                    .name(inputParts[0])
                    .value(inputParts[1])
                    .build();
            insertColumnDefinitions.add(insertColumnDefinition);
        }
        return insertColumnDefinitions;
    }

}
