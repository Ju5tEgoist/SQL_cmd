package com.company;

import com.company.Command.CommandList;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by yulia on 29.10.16.
 */
public class Main {
    Connection connection;
    String database;
    String user;
    String password;
    ContentsOfTheTables contentsOfTheTables;
    String[] tableNames;
    String chosenTableName;
    String records;
    Statement statement;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Main main = new Main();
        main.welcomeDatabase();
    }

    public void welcomeDatabase() throws SQLException, ClassNotFoundException {
        System.out.println("Hi, I'm Database manager! ");
        System.out.println("Please, write name of database in which you want to work, username and password in format: nameOfDataBase|username|password ");

        CommandList commandList = new CommandList(connection,  database, user, password, contentsOfTheTables, tableNames, chosenTableName,  records, statement);
        commandList.doCommand();



    }
}
