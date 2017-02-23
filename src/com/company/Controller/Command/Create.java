package com.company.Controller.Command;


import com.company.model.CreateTableQueryBuilder;
import com.company.model.DatabaseList;
import com.company.view.ConsoleReader;

import java.sql.SQLException;

/**
 * Created by yulia on 21.02.17.
 */
public class Create implements Command {
    @Override
    public boolean shouldProcess(String command) {
        return "create".equals(command);
    }

    @Override
    public void process(String command) throws SQLException, ClassNotFoundException {
        DatabaseList databaseList = new DatabaseList();
        String tableName = databaseList.getTableName();
        System.out.println("Please, type the number of columns");
        ConsoleReader consoleReader = new ConsoleReader();
        Integer columnNumber = Integer.valueOf(consoleReader.read());
        CreateTableQueryBuilder createTableQueryBuilder = new CreateTableQueryBuilder();
        createTableQueryBuilder.queryBuilder(columnNumber, tableName);
        System.out.println(tableName + " was created");
    }
}
