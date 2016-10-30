package com.company;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by yulia on 02.10.16.
 */
public class UserConnection implements ConsoleManager {

    ConsoleReader consoleReader;
    DatabaseManager databaseManager;
    ContentsOfTheTables contentsOfTheTables;
    private TableList tableList;

    UserConnection(ConsoleReader consoleReader, DatabaseManager databaseManager, ContentsOfTheTables contentsOfTheTables){
        this.consoleReader = new ConsoleReader();
        this.databaseManager = new DatabaseManager();
        this.contentsOfTheTables = new ContentsOfTheTables();
      //  this.tableList = tableList;
    }

    public Connection getConnectionData() {
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
            equalCommand();
            tableList.getTableForView(databaseName);
        }catch (SQLException e){
            System.out.println("Exception in getConnectionData");
        }
    return connection;
    }

    public boolean equalCommand() throws SQLException {
        boolean result = false;
        if(consoleReader.read().equals("list")){
            result = true;
        }
        else {
            System.out.println("Unfortunately, this command does not exist. Please, try again");
            equalCommand();
        }
        return result;
    }




    @Override
    public String read() {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        return input;
    }
}
