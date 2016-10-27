package com.company;

import java.util.Scanner;

/**
 * Created by yulia on 02.10.16.
 */
public class UserConnection implements ConsoleManager {

    ConsoleReader consoleReader = new ConsoleReader();
    DatabaseManager databaseManager = new DatabaseManager();

    public void getConnectionData() {
        boolean result = false;

        while (!result) {
            try {
                String string = consoleReader.read();
                String[] data = string.split("\\|");
                if (data.length != 3) {
                    throw new IllegalArgumentException("Wrong parameters number. Your number is " + data.length + " But must be 3");
                }
                String databaseName = data[0];
                String userName = data[1];
                String password = data[2];

                result = !databaseManager.connect(databaseName, userName, password).equals(null);
            } catch (Exception e) {
                System.out.println(e.toString());
                System.out.println("Check your entries and try again");
            }
        }
    }


    public void welcomeDatabase() {
        System.out.println("Hi, I'm Database manager! ");
        System.out.println("Please, write name of database in which you want to work, username and password in format: nameOfDataBase|username|password ");
        getConnectionData();

    }

    @Override
    public String read() {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        return input;
    }
}
