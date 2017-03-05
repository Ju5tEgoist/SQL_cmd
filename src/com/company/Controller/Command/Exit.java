package com.company.Controller.Command;

/**
 * Created by yulia on 07.11.16.
 */
public class Exit extends AbstractCommand {

    @Override
    public boolean shouldProcess(String command) {
        return "exit".equals(command);
    }

    @Override
    public void execute(String command){
        throw new ExitException();
    }
}
