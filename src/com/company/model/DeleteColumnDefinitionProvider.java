package com.company.model;

import com.company.view.ConsoleReader;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yulia on 23.02.17.
 */
public class DeleteColumnDefinitionProvider {
    protected List getProperties() throws SQLException {
        List<InsertUpdateDeleteColumnDefinition> deleteDeleteColumnDefinitions = new ArrayList<>();
        ConsoleReader consoleReader = new ConsoleReader();
            System.out.println("Now enter column's name and value which you want delete: <name/value>");
            String[] inputParts = consoleReader.read().split("/");
            InsertUpdateDeleteColumnDefinition deleteDeleteColumnDefinition = InsertUpdateDeleteColumnDefinition.builder()
                    .name(inputParts[0])
                    .value(inputParts[1])
                    .build();
            deleteDeleteColumnDefinitions.add(deleteDeleteColumnDefinition);
        return deleteDeleteColumnDefinitions;
    }
}
