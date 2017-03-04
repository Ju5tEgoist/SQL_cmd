package com.company.Controller.Command;

import com.company.model.DatabaseManager;

/**
 * Created by yulia on 02.03.17.
 */
public abstract class AbstractCommand implements Command {
    private DatabaseManager databaseManager;

    public AbstractCommand() {
        databaseManager = new DatabaseManager();
    }

    protected AbstractCommand(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    protected DatabaseManager getDatabaseManager() {
        return databaseManager;
    }
}
