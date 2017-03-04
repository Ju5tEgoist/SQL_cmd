package com.company.Controller.Command;

import com.company.model.DatabaseManager;
import com.company.model.FindProperties;
import com.company.model.FindProvider;
import com.company.view.ConsoleReader;
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

    public static String selectedTableName;

    @Override
    public boolean shouldProcess(String command) {
        return command != null && command.startsWith("find");
    }

    @Override
    public void process(String database) throws SQLException {
        FindProperties findProperties = new FindProperties();
        System.out.println("For view table, please, enter the name: find <tableName> or find <tableName LIMIT/OFFSET>");
        String[] tableNames = findProperties.getTableNames(database);
        ConsoleReader consoleReader = new ConsoleReader();
        String result = consoleReader.read();
        String[] parts = result.split(" ") ;
        FindProvider findProvider = new FindProvider();
        selectedTableName = findProvider.getSelectedTableName(tableNames, result, parts, selectedTableName);
    }
}
