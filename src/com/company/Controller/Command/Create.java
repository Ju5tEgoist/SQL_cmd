package com.company.Controller.Command;

import com.company.model.CreateTableQueryBuilder;
import com.company.model.DatabaseManager;
import com.company.model.DatabaseProperties;
import com.company.view.ScannerConsoleReader;

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
    public void execute(String command) throws SQLException, ClassNotFoundException {
        DatabaseProperties databaseProperties = new DatabaseProperties();
        String tableName = databaseProperties.getTableName();
        System.out.println("Please, type the number of columns");
        ScannerConsoleReader scannerConsoleReader = new ScannerConsoleReader();
        Integer columnNumber = Integer.valueOf(scannerConsoleReader.read());
        CreateTableQueryBuilder createTableQueryBuilder = new CreateTableQueryBuilder();
        createTableQueryBuilder.queryBuild(columnNumber, tableName);
        System.out.println(tableName + " was created");
    }
}
