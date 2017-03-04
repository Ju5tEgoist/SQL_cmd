package com.company.model;

import com.company.view.ConsoleReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yulia on 23.02.17.
 */
public class DeleteProvider {
    public List getProperties() throws SQLException {
        List<InsertUpdateDeleteColumnDefinition> deleteColumnDefinitions = new ArrayList<>();
        ConsoleReader consoleReader = new ConsoleReader();
            System.out.println("Now enter column's name and value which you want delete: <name/value>");
            String[] inputParts = consoleReader.read().split("/");
            InsertUpdateDeleteColumnDefinition deleteColumnDefinition = InsertUpdateDeleteColumnDefinition.builder()
                    .name(inputParts[0])
                    .value(inputParts[1])
                    .build();
            deleteColumnDefinitions.add(deleteColumnDefinition);
        return deleteColumnDefinitions;
    }
}
