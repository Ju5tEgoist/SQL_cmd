package com.company.controller.command;

import com.company.model.exception.ExitException;

/**
 * Created by yulia on 07.11.16.
 */
public class Exit implements Command {

    @Override
    public boolean shouldExecute(String command) {
        return "exit".equals(command);
    }

    @Override
    public void execute(){
        throw new ExitException();
    }
}
