package com.company.Controller.Command;

/**
 * Created by yulia on 07.11.16.
 */
public class Exit implements Command {

    @Override
    public boolean shouldProcess(String command) {
        return "exit".equals(command);
    }

    @Override
    public void process(String command){
        throw new ExitException();
    }
}
