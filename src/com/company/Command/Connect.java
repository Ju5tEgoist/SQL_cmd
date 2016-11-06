package com.company.Command;

import com.company.*;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by yulia on 06.11.16.
 */
public class Connect implements Command {
    ConsoleReader consoleReader;
    DatabaseManager databaseManager;
    ContentsOfTheTables contentsOfTheTables;
    private DatabaseList tableList;
    UserConnection userConnection;

   Connect(){
        this.consoleReader = new ConsoleReader();
        this.databaseManager = new DatabaseManager();
        this.contentsOfTheTables = new ContentsOfTheTables();
        this.userConnection = new UserConnection();
    }

    @Override
    public boolean isProcess(String command) {
        return command.equals("connect");
    }

    @Override
    public void process(String command) {
        boolean result = false;
        Connection connection = null;
        String databaseName = "";
        while (!result) {
            try {
                String string = consoleReader.read();
                String[] data = string.split("\\|");
                if (data.length != 3) {
                    throw new IllegalArgumentException("Wrong parameters number. Your number is " + data.length + " But must be 3");
                }
                databaseName = data[0];
                String userName = data[1];
                String password = data[2];
                connection = databaseManager.connect(databaseName, userName, password);
                tableList = new DatabaseList(connection);

                result = connection != null;
            } catch (Exception e) {
                System.out.println(e.toString());
                System.out.println("Check your entries and try again");
            }
        }
        System.out.println("If you want review all user's tables, please, enter command 'list'");

        try {
            userConnection.equalCommand();
            tableList.getTableForView(databaseName);
        }catch (SQLException e){
            System.out.println("Exception in getConnectionData");
        }
    }
}
