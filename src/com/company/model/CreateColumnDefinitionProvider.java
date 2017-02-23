package com.company.model;

import com.company.view.ConsoleReader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yulia on 21.02.17.
 */
public class CreateColumnDefinitionProvider {
    protected List getProperties(Integer columnNumber) {
        java.util.List<CreateColumnDefinition> columnDefinitions = new ArrayList<>();
        for (int i = 0; i < columnNumber; i++) {
            System.out.println("Now enter column's name and type of this column: <name/type>");
            ConsoleReader consoleReader = new ConsoleReader();
            String[] inputParts = consoleReader.read().split("/");
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
