package com.company.Controller.Command;

import com.company.model.DatabaseManager;

import java.sql.SQLException;

/**
 * Created by yulia on 08.11.16.
 */
public class Help extends AbstractCommand {

    public Help() {
    }

    public Help(DatabaseManager databaseManager) {
        super(databaseManager);
    }

    private static final String INFO = "All available command: " +
            "\nhelp - show all command" +
            "\nconnect - connect to database" +
            "\nclear - clears the contents of the specified table" +
            "\ndrop - drops the specified table" +
            "\ncreate - create new table" +
            "\ninsert - add row in the table" +
            "\nupdate - change value" +
            "\ndelete - delete value" +
            "\nlist - to review all user's tables" +
            "\nfind - to find and view table in database" +
            "\nexit";

    @Override
    public boolean shouldProcess(String command) {
        return "help".equals(command);
    }

    @Override
    public void execute(String command) throws SQLException, ClassNotFoundException {
            System.out.println(INFO);

    }
}
