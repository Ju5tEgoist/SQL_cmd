package com.company.Controller.Command;

import com.company.model.DatabaseProperties;
import com.company.model.DatabaseManager;
import com.company.model.UpdateTableQueryBuilder;
import com.company.view.ScannerConsoleReader;
import com.company.view.TablePresenter;

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
    public void execute(String command) throws SQLException, ClassNotFoundException {
        DatabaseProperties databaseProperties = new DatabaseProperties();
        String tableName = databaseProperties.getTableName();
        TablePresenter tablePresenter = new TablePresenter();
        System.out.println("This table: ");
        ResultSet rs = getResultSet(tableName);
        tablePresenter.showTable(tableName);
        int columnNumber = getColumnNumber();
        UpdateTableQueryBuilder updateTableQueryBuilder = new UpdateTableQueryBuilder();
        updateTableQueryBuilder.queryBuild(tableName, rs, columnNumber);
        tablePresenter.showTable(tableName);
    }

    private int getColumnNumber() {
        System.out.println("Now enter the column number in which you want change value");
        ScannerConsoleReader scannerConsoleReader = new ScannerConsoleReader();
        return Integer.valueOf(scannerConsoleReader.read());
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
