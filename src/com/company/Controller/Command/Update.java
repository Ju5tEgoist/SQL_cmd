package com.company.Controller.Command;

import com.company.model.DatabasePropertiesProvider;
import com.company.model.DatabaseManager;
import com.company.model.UpdateTableQueryBuilder;
import com.company.view.ConsoleReader;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yulia on 21.02.17.
 */
public class Update implements Command {
    @Override
    public boolean shouldProcess(String command) {
        return "update".equals(command);
    }

    @Override
    public void process(String command) throws SQLException, ClassNotFoundException {
        DatabasePropertiesProvider databasePropertiesProvider = new DatabasePropertiesProvider();
        String tableName = databasePropertiesProvider.getTableName();
        System.out.println("This table: ");
        ResultSet rs = DatabaseManager.getStatement().executeQuery("SELECT * FROM public." + tableName);
        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            if(i == 1){
                System.out.print(rs.getMetaData().getColumnName(i));
            }
            else { System.out.println("|" + rs.getMetaData().getColumnName(i)); }
        }
        databasePropertiesProvider.getTableForView(tableName);
        System.out.println("Now enter the column number in which you want change value");
        ConsoleReader consoleReader = new ConsoleReader();
        int columnNumber = Integer.valueOf(consoleReader.read());
        UpdateTableQueryBuilder updateTableQueryBuilder = new UpdateTableQueryBuilder();
        updateTableQueryBuilder.queryBuilder(tableName, rs, columnNumber);
        databasePropertiesProvider.getTableForView(tableName);
    }
}
