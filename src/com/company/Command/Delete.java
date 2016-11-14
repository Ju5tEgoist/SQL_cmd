package com.company.Command;

import java.sql.Connection;

/**
 * Created by yulia on 06.11.16.
 */
public class Delete implements Command {
    @Override
    public boolean shouldProcess(String command) {
        return false;
    }

    @Override
    public void process(String command) {

    }
}
