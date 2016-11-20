package com.company;

import com.company.Command.Introduction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by yulia on 29.10.16.
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Main main = new Main();
        main.welcomeDatabase();
    }

    public void welcomeDatabase() throws SQLException, ClassNotFoundException {
        System.out.println("Hi, I'm Database manager! ");
        Introduction introduction = new Introduction();
        introduction.doCommand();



    }
}
