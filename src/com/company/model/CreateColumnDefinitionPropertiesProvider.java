package com.company.model;

import com.company.view.ConsoleManager;
import com.company.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yulia on 21.02.17.
 */
public class CreateColumnDefinitionPropertiesProvider {
    View view = new ConsoleManager();
    public List<CreateColumnDefinition> getProperties(Integer columnNumber) {
        java.util.List<CreateColumnDefinition> columnDefinitions = new ArrayList<>();
        for (int i = 0; i < columnNumber; i++) {
            view.write("Now enter column's name and type of this column: <name/type>");
            String[] inputParts = view.read().split("/");
            CreateColumnDefinition columnDefinition = CreateColumnDefinition.builder()
                    .name(inputParts[0])
                    .nullable(false)
                    .dataType(inputParts[1])
                    .build();
            columnDefinitions.add(columnDefinition);
        }
        return columnDefinitions;
    }
}
