package com.company.Controller.Command;

import java.sql.SQLException;

/**
 * Created by yulia on 06.11.16.
 */
public interface Command {
    boolean shouldProcess(String command);
    void execute(String command)throws SQLException, ClassNotFoundException;
}
