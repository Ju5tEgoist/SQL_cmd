package com.company.Controller.Command;

import com.company.model.DatabasePropertiesProvider;
import com.company.model.DatabaseManager;
import com.company.model.UpdateTableQueryBuilder;
import com.company.view.ConsoleReader;
import com.company.view.TablePresentation;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yulia on 21.02.17.
 */
public class Update extends AbstractCommand {

    public Update() {
    }

    public Update(DatabaseManager databaseManager) {
        super(databaseManager);
    }

    @Override
    public boolean shouldProcess(String command) {
        return "update".equals(command);
    }

    @Override
    public void process(String command) throws SQLException, ClassNotFoundException {
        DatabasePropertiesProvider databasePropertiesProvider = new DatabasePropertiesProvider();
        String tableName = databasePropertiesProvider.getTableName();
        TablePresentation tablePresentation = new TablePresentation();
        System.out.println("This table: ");
        ResultSet rs = getResultSet(tableName);
        tablePresentation.showTable(tableName);
        int columnNumber = getColumnNumber();
        UpdateTableQueryBuilder updateTableQueryBuilder = new UpdateTableQueryBuilder();
        updateTableQueryBuilder.queryBuilder(tableName, rs, columnNumber);
        tablePresentation.showTable(tableName);
    }

    private int getColumnNumber() {
        System.out.println("Now enter the column number in which you want change value");
        ConsoleReader consoleReader = new ConsoleReader();
        return Integer.valueOf(consoleReader.read());
    }

    private ResultSet getResultSet(String tableName) throws SQLException {
        ResultSet rs = getDatabaseManager().getStatement().executeQuery("SELECT * FROM public." + tableName);
        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            String columnName = rs.getMetaData().getColumnName(i);
            System.out.print(i > 1 ? "|" + columnName : columnName);
        }
        return rs;
    }
}
