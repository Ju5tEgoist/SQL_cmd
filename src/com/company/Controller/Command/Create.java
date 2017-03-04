package com.company.Controller.Command;

import com.company.model.CreateTableQueryBuilder;
import com.company.model.DatabaseManager;
import com.company.model.DatabasePropertiesProvider;
import com.company.view.ConsoleReader;

import java.sql.SQLException;

/**
 * Created by yulia on 21.02.17.
 */
public class Create extends AbstractCommand {
    public Create() {
    }

    public Create(DatabaseManager databaseManager) {
        super(databaseManager);
    }

    @Override
    public boolean shouldProcess(String command) {
        return "create".equals(command);
    }

    @Override
    public void process(String command) throws SQLException, ClassNotFoundException {
        DatabasePropertiesProvider databasePropertiesProvider = new DatabasePropertiesProvider();
        String tableName = databasePropertiesProvider.getTableName();
        System.out.println("Please, type the number of columns");
        ConsoleReader consoleReader = new ConsoleReader();
        Integer columnNumber = Integer.valueOf(consoleReader.read());
        CreateTableQueryBuilder createTableQueryBuilder = new CreateTableQueryBuilder();
        createTableQueryBuilder.queryBuildExecute(columnNumber, tableName);
        System.out.println(tableName + " was created");
    }
}
