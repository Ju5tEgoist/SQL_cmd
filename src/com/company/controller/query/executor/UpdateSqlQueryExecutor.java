package com.company.controller.query.executor;

import java.sql.SQLException;

/**
 * Created by yulia on 05.03.17.
 */
public class UpdateSqlQueryExecutor extends DatabaseManagerSqlQueryExecutor {

    @Override
    public void execute(String query) throws SQLException {
        getDatabaseManager().getStatement().executeUpdate(query);
    }
}
