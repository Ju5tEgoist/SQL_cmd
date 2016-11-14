package com.company.Command;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by yulia on 07.11.16.
 */
public class Exit implements Command {

    @Override
    public boolean shouldProcess(String command) {
        return command.equals("exit");
    }

    @Override
    public void process(String command) throws SQLException {
        System.exit(0);
    }
}
