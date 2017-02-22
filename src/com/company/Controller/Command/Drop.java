package com.company.Controller.Command;

import com.company.model.DatabaseList;
import com.company.model.DatabaseManager;

import java.sql.SQLException;

/**
 * Created by yulia on 21.02.17.
 */
public class Drop implements Command {


    @Override
    public boolean shouldProcess(String command) {
        return "drop".equals(command);
    }

    @Override
    public void process(String command) throws SQLException, ClassNotFoundException {
        DatabaseList databaseList = new DatabaseList();
        String tableName = databaseList.getTableName();
        String sql = "DROP TABLE public." + tableName;
        DatabaseManager.getStatement().executeUpdate(sql);
        System.out.println(tableName + " is dropped");
    }
}
