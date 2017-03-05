package com.company.Controller.Command;

import com.company.model.DatabaseManager;
import com.company.model.FindProperties;
import com.company.view.ScannerConsoleReader;
import java.sql.SQLException;

/**
 * Created by yulia on 06.11.16.
 */
public class Find extends AbstractCommand {

    public Find() {
    }

    public Find(DatabaseManager databaseManager) {
        super(databaseManager);
    }

    @Override
    public boolean shouldProcess(String command) {
        return command.startsWith("find");
    }

    @Override
    public void execute(String database) throws SQLException {
        FindProperties findProperties = new FindProperties();
        System.out.println("For view table, please, enter the name: find <tableName> or find <tableName LIMIT/OFFSET>");
        String[] tableNames = findProperties.getTableNames(database);
        ScannerConsoleReader scannerConsoleReader = new ScannerConsoleReader();
        String result = scannerConsoleReader.read();
        String[] parts = result.split(" ") ;
        findProperties.getSelectedTableName(tableNames, result, parts);
    }
}
