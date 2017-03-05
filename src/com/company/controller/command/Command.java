package com.company.controller.command;

import com.company.model.exception.CommandExecutionException;

import java.sql.SQLException;

/**
 * Created by yulia on 06.11.16.
 */
public interface Command {
    boolean shouldExecute(String command);

    void execute() throws CommandExecutionException, SQLException;
}
