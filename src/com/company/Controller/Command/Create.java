package com.company.Controller.Command;


import com.company.model.CreateTableQueryBuilder;
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
        System.out.println("Enter new table name");
        ConsoleReader consoleReader = new ConsoleReader();
        String tableName = consoleReader.read();
        System.out.println("Please, type the number of columns");
        Integer columnNumber = Integer.valueOf(consoleReader.read());
        CreateTableQueryBuilder createTableQueryBuilder = new CreateTableQueryBuilder();
        createTableQueryBuilder.queryBuilder(columnNumber, tableName);

    }


}
