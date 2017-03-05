package com.company.controller.command;

import com.company.controller.query.parameter.ConnectParameters;
import com.company.model.FindProperties;
import com.company.view.ScannerConsoleReader;
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
        System.out.println("For view table, please, enter the name: find <tableName> or find <tableName LIMIT/OFFSET>");
        String[] tableNames = findProperties.getTableNames(new ConnectParameters().getDatabase());
        ScannerConsoleReader scannerConsoleReader = new ScannerConsoleReader();
        String result = scannerConsoleReader.read();
        String[] parts = result.split(" ") ;
        findProperties.getSelectedTableName(tableNames, result, parts);
    }
}
