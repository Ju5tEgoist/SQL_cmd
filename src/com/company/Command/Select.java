package com.company.Command;

/**
 * Created by yulia on 06.11.16.
 */
public class Select implements Command {
    @Override
    public boolean isProcess(String command) {
        return false;
    }

    @Override
    public void process(String command) {

    }
}
