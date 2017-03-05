package com.company.Controller.Command;

import com.company.model.DatabaseManager;

import java.sql.SQLException;

/**
 * Created by yulia on 06.11.16.
 */
public class Connect extends AbstractCommand {

    public Connect() {}
    public Connect(DatabaseManager databaseManager) {
        super(databaseManager);
    }

    @Override
    public boolean shouldProcess(String command) {
        return "connect".equals(command);
    }

    @Override
    public void execute(String command) throws SQLException, ClassNotFoundException {
        boolean connectionSuccessful = false;
        System.out.println("Please, write name of database in which you want to work, username and password in format: dataBaseName|username|password ");
        while (!connectionSuccessful) {
            try {
                connectionSuccessful = isConnectionSuccessful();
            } catch (Exception e) {
                System.out.println(e.toString());
                System.out.println("Check your entries and try again");
            }
        }

    }

    public boolean isConnectionSuccessful() {
        return DatabaseManager.getConnection() != null ;
    }
}
