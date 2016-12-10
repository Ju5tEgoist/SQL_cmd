package com.company.Controller.Command;


import com.company.Controller.Command.Command;

import java.sql.SQLException;

/**
 * Created by yulia on 08.11.16.
 */
public class CommandList implements Command {

    public static final String INFO = "All available command: " +
            "\nconnect - connect to database" +
            "\nlist - to review all user's tables" +
            "\nfind - to find and view table in database" +
            "\nchange - to change data in the table" +
            "\nexit";

    @Override
    public boolean shouldProcess(String command) {
        return "command list".equals(command);
    }

    @Override
    public void process(String command) throws SQLException, ClassNotFoundException {
            System.out.println(INFO);

    }
}
