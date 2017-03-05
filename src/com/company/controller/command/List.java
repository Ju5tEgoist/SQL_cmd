package com.company.controller.command;

import com.company.controller.query.parameter.ConnectParameters;
import com.company.model.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yulia on 06.11.16.
 */
public class List implements Command {

    @Override
    public boolean shouldExecute(String command) {
        return "list".equals(command);
    }

    @Override
    public void execute() throws SQLException {
        ResultSet list = DatabaseManager.getConnection().getMetaData().getTables(new ConnectParameters()
                .getDatabase(), "public", "%", null);
        System.out.printf("List of all available tables: [");
        int databaseIndex = 3;
        while (list.next()) {
            String tableName = list.getString(databaseIndex);
            System.out.print(tableName);
            System.out.print(list.isLast() ? "]" : ", ");
        }
    }
}
