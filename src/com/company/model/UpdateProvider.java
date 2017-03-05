package com.company.model;

import com.company.view.ScannerConsoleReader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yulia on 23.02.17.
 */
public class UpdateProvider {
    public List getProperties( ResultSet rs) throws SQLException {

        List<InsertUpdateDeleteColumnDefinition> updateDeleteColumnDefinitions = new ArrayList<>();
        ScannerConsoleReader scannerConsoleReader = new ScannerConsoleReader();
        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            System.out.println("Now enter column's name and value: <name/value>. For column which changes type new value");
            String[] inputParts = scannerConsoleReader.read().split("/");
            InsertUpdateDeleteColumnDefinition updateDeleteColumnDefinition = InsertUpdateDeleteColumnDefinition.builder()
                    .name(inputParts[0])
                    .value(inputParts[1])
                    .build();
            updateDeleteColumnDefinitions.add(updateDeleteColumnDefinition);
        }
        return updateDeleteColumnDefinitions;
    }
}
