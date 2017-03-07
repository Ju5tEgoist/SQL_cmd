package com.company.controller.command;

import com.company.controller.query.parameter.ConnectParameters;
import com.company.model.FindProperties;
import com.company.view.ConsoleManager;
import com.company.view.View;

import java.sql.SQLException;

/**
 * Created by yulia on 06.11.16.
 */
public class Find implements Command {

    @Override
    public boolean shouldExecute(String command) {
        return command.startsWith("find");
    }

    @Override
    public void execute() throws SQLException {
        FindProperties findProperties = new FindProperties();
        View view = new ConsoleManager();
        view.write("For view table, please, enter the name: find <tableName> or find <tableName LIMIT/OFFSET>");
        String[] tableNames = findProperties.getTableNames(new ConnectParameters().getDatabase());
        String result = view.read();
        String[] parts = result.split(" ") ;
        findProperties.getSelectedTableName(tableNames, result, parts);
    }
}
