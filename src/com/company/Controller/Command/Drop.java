package com.company.Controller.Command;

import com.company.model.DatabaseProperties;
import com.company.model.DatabaseManager;

import java.sql.SQLException;

/**
 * Created by yulia on 21.02.17.
 */
public class Drop extends AbstractCommand {

    public Drop() {
    }

    public Drop(DatabaseManager databaseManager) {
        super(databaseManager);
    }

    @Override
    public boolean shouldProcess(String command) {
        return "drop".equals(command);
    }

    @Override
    public void execute(String command) throws SQLException, ClassNotFoundException {
        DatabaseProperties databaseProperties = new DatabaseProperties();
        String tableName = databaseProperties.getTableName();
        String sql = "DROP TABLE public." + tableName;
        getDatabaseManager().getStatement().executeUpdate(sql);
        System.out.println(tableName + " is dropped");
    }
}
