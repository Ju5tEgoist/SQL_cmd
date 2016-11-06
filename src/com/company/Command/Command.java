package com.company.Command;

import java.sql.SQLException;

/**
 * Created by yulia on 06.11.16.
 */
public interface Command {
    boolean isProcess(String command);
    void process(String command)throws SQLException ;
}
