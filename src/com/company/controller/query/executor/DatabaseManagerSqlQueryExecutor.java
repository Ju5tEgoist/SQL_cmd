package com.company.controller.query.executor;

import com.company.model.DatabaseManager;

/**
 * Created by yulia on 05.03.17.
 */
public abstract class DatabaseManagerSqlQueryExecutor implements SqlQueryExecutor {
    private DatabaseManager databaseManager;

    protected DatabaseManagerSqlQueryExecutor() {
        this.databaseManager = new DatabaseManager();
    }

    protected DatabaseManager getDatabaseManager() {
        return databaseManager;
    }
}
