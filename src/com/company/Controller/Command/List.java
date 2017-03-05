package com.company.Controller.Command;


import com.company.model.DatabaseManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yulia on 06.11.16.
 */
public class List extends AbstractCommand {

    public List() {
    }

    public List(DatabaseManager databaseManager) {
        super(databaseManager);
    }

    @Override
    public boolean shouldProcess(String command) {
        return "list".equals(command);
    }

    @Override
    public void execute(String database) throws SQLException, ClassNotFoundException {
        Connection connect = DatabaseManager.getConnection();
        ResultSet list = connect.getMetaData().getTables(database, "public", "%", null);
        System.out.printf("List of all available tables: [");
        int databaseIndex = 3;
        while (list.next()) {
            String tableName = list.getString(databaseIndex);
            System.out.print(tableName);
            System.out.print(list.isLast() ? "]" : ", ");
        }
    }

}
