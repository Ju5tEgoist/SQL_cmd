package com.company.Controller.Command;

import com.company.model.DatabaseManager;
import com.company.model.DatabasePropertiesProvider;
import java.sql.SQLException;

/**
 * Created by yulia on 21.02.17.
 */
public class Clear extends AbstractCommand{

    public Clear() {
        super();
    }

    public Clear(DatabaseManager databaseManager){
        super(databaseManager);
    }

    @Override
    public boolean shouldProcess(String command) {
        return "clear".equals(command);
    }

    @Override
    public void process(String command) throws SQLException, ClassNotFoundException {
        DatabasePropertiesProvider databasePropertiesProvider = new DatabasePropertiesProvider();
        String tableName = databasePropertiesProvider.getTableName();
        String sql = "DELETE FROM public." + tableName;
        getDatabaseManager().getStatement().executeUpdate(sql);
        System.out.println(tableName + " was cleaned");
    }
}
