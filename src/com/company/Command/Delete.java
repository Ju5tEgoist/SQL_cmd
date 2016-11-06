package com.company.Command;

/**
 * Created by yulia on 06.11.16.
 */
public class Delete implements Command {
    @Override
    public boolean isProcess(String command) {
        return false;
    }

    @Override
    public void process(String command) {

    }
}
