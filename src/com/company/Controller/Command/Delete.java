package com.company.Controller.Command;

import com.company.model.DatabaseProperties;
import com.company.model.DatabaseManager;
import com.company.model.DeleteTableQueryBuilder;
import com.company.view.TablePresenter;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Created by yulia on 21.02.17.
 */
public class Delete extends AbstractCommand {

    public Delete() {
    }

    public Delete(DatabaseManager databaseManager) {
        super(databaseManager);
    }

    @Override
    public boolean shouldProcess(String command) {
        return "delete".equals(command);
    }

    @Override
    public void execute(String command) throws SQLException, ClassNotFoundException {
        TablePresenter tablePresenter = new TablePresenter();
        DatabaseProperties databaseProperties = new DatabaseProperties();
        String tableName = databaseProperties.getTableName();
        showColumns(tableName);
        tablePresenter.showTable(tableName);
        DeleteTableQueryBuilder updateTableQueryBuilder = new DeleteTableQueryBuilder();
        updateTableQueryBuilder.queryBuild(tableName);
        tablePresenter.showTable(tableName);
    }

    private void showColumns(String tableName) throws SQLException {
        System.out.println("This table: ");
        ResultSet rs = getDatabaseManager().getStatement().executeQuery("SELECT * FROM public." + tableName);
        ResultSetMetaData metaData = rs.getMetaData();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            System.out.print(i > 1 ? "|" + metaData.getColumnName(i) : metaData.getColumnName(i));
        }
    }
}
