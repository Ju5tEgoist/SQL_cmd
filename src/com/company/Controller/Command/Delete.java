package com.company.Controller.Command;

import com.company.model.DatabaseList;
import com.company.model.DatabaseManager;
import com.company.model.DeleteTableQueryBuilder;
import com.company.model.UpdateTableQueryBuilder;
import com.company.view.ConsoleReader;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yulia on 21.02.17.
 */
public class Delete implements Command {
    @Override
    public boolean shouldProcess(String command) {
        return "delete".equals(command);
    }

    @Override
    public void process(String command) throws SQLException, ClassNotFoundException {
        DatabaseList databaseList = new DatabaseList();
        String tableName = databaseList.getTableName();
        System.out.println("This table: ");
        ResultSet rs = DatabaseManager.getStatement().executeQuery("SELECT * FROM public." + tableName);
        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            if(i == 1){
                System.out.print(rs.getMetaData().getColumnName(i));
            }
            else { System.out.println( "|" + rs.getMetaData().getColumnName(i)); }
        }
        databaseList.getTableForView(tableName);
        DeleteTableQueryBuilder updateTableQueryBuilder = new DeleteTableQueryBuilder();
        updateTableQueryBuilder.queryBuilder(tableName);
        databaseList.getTableForView(tableName);
    }
}
