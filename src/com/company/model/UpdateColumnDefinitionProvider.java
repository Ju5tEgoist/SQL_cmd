package com.company.model;

import com.company.view.ConsoleReader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yulia on 23.02.17.
 */
public class UpdateColumnDefinitionProvider {
    protected List getProperties( ResultSet rs) throws SQLException {
        List<InsertUpdateDeleteColumnDefinition> updateDeleteColumnDefinitions = new ArrayList<>();
        ConsoleReader consoleReader = new ConsoleReader();
        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            System.out.println("Now enter column's name and value: <name/value>. For column which changes type new value");
            String[] inputParts = consoleReader.read().split("/");
            InsertUpdateDeleteColumnDefinition updateDeleteColumnDefinition = InsertUpdateDeleteColumnDefinition.builder()
                    .name(inputParts[0])
                    .value(inputParts[1])
                    .build();
            updateDeleteColumnDefinitions.add(updateDeleteColumnDefinition);
        }
        return updateDeleteColumnDefinitions;
    }
}
