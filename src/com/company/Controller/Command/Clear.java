package com.company.Controller.Command;

import com.company.model.DatabaseManager;
import com.company.model.DatabaseProperties;
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
    public void execute(String command) throws SQLException, ClassNotFoundException {
        DatabaseProperties databaseProperties = new DatabaseProperties();
        String tableName = databaseProperties.getTableName();
        String sql = "DELETE FROM public." + tableName;
        getDatabaseManager().getStatement().executeUpdate(sql);
        System.out.println(tableName + " was cleaned");
    }
}
