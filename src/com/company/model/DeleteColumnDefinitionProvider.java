package com.company.model;

import com.company.view.ConsoleManager;
import com.company.view.View;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yulia on 23.02.17.
 */
public class DeleteColumnDefinitionProvider {
    View view = new ConsoleManager();
    public List getProperties() throws SQLException {
        List<InsertUpdateDeleteColumnDefinition> deleteColumnDefinitions = new ArrayList<>();
            view.write("Now enter column's name and value which you want delete: <name/value>");
            String[] inputParts = view.read().split("/");
            InsertUpdateDeleteColumnDefinition deleteColumnDefinition = InsertUpdateDeleteColumnDefinition.builder()
                    .name(inputParts[0])
                    .value(inputParts[1])
                    .build();
            deleteColumnDefinitions.add(deleteColumnDefinition);
        return deleteColumnDefinitions;
    }
}
