package com.company.Controller.Command;

import com.company.model.DatabasePropertiesProvider;
import com.company.model.DatabaseManager;
import com.company.model.InsertTableQueryBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yulia on 21.02.17.
 */
public class Insert extends AbstractCommand {

    public Insert() {
    }

    public Insert(DatabaseManager databaseManager) {
        super(databaseManager);
    }

    @Override
    public boolean shouldProcess(String command) {
        return "insert".equals(command);
    }

    @Override
    public void process(String command) throws SQLException, ClassNotFoundException {
        DatabasePropertiesProvider databasePropertiesProvider = new DatabasePropertiesProvider();
        String tableName = databasePropertiesProvider.getTableName();
        ResultSet rs = getDatabaseManager().getStatement().executeQuery("SELECT * FROM public." + tableName);
        InsertTableQueryBuilder insertTableQueryBuilder = new InsertTableQueryBuilder();
        insertTableQueryBuilder.queryBuilderExecute(tableName, rs);
        System.out.println("Your data were added");
    }
}
