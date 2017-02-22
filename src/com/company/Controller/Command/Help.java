package com.company.Controller.Command;

import java.sql.SQLException;

/**
 * Created by yulia on 08.11.16.
 */
public class Help implements Command {

    public static final String INFO = "All available command: " +
            "\nhelp - show all command" +
            "\nconnect - connect to database" +
            "\nclear - clears the contents of the specified table" +
            "\ndrop - drops the specified table" +
            "\ncreate - create new table" +
            "\nlist - to review all user's tables" +
            "\nfind - to find and view table in database" +
            "\nchange - to change data in the table" +
            "\nexit";

    @Override
    public boolean shouldProcess(String command) {
        return "help".equals(command);
    }

    @Override
    public void process(String command) throws SQLException, ClassNotFoundException {
            System.out.println(INFO);

    }
}
