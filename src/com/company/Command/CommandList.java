package com.company.Command;

import java.sql.SQLException;

/**
 * Created by yulia on 08.11.16.
 */
public class CommandList implements Command{

    public static final String INFO = "All available command: " +
            "\nconnect - connect to database" +
            "\nlist - to review all user's tables" +
            "\nfind <tableName> - to find and view table in database" +
            "\nfind <tableName Limit/Offset> - to find and view part of table in database" +
            "\nchange <columnNumber|rowNumber> - to change data in the table" +
            "\ninsert - to add new rows of data to a table in the database" +
            "\nselect - to select data from the database" +
            "\ndelete - to delete records in the table" +
            "\nupdate - to update records in a table" +
            "\nexit";

    @Override
    public boolean shouldProcess(String command) {
        return "command list".equals(command);
    }

    @Override
    public void process(String command) throws SQLException, ClassNotFoundException {
            System.out.println(INFO);
    }
}
