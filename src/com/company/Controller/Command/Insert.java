package com.company.Controller.Command;

import com.company.model.DatabaseManager;
import com.company.model.InsertTableQueryBuilder;
import com.company.view.ConsoleReader;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yulia on 21.02.17.
 */
public class Insert implements Command {
    @Override
    public boolean shouldProcess(String command) {
        return "insert".equals(command);
    }

    @Override
    public void process(String command) throws SQLException, ClassNotFoundException {
        System.out.println("Enter table name");
        ConsoleReader consoleReader = new ConsoleReader();
        String tableName = consoleReader.read();
        ResultSet rs = DatabaseManager.getStatement().executeQuery("SELECT * FROM public." + tableName);
        InsertTableQueryBuilder insertTableQueryBuilder = new InsertTableQueryBuilder();
        insertTableQueryBuilder.queryBuilder(tableName, rs);
        System.out.println("Your data are added");
    }
}
