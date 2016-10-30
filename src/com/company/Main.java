package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by yulia on 29.10.16.
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Main main = new Main();
        main.welcomeDatabase();
//
//        //insert
//        String sql = "INSERT INTO public.user " + "VALUES('Rick', '999080', 9)";
//        stmt.executeUpdate(sql);
//
//        //select
//        rs = stmt.executeQuery( "SELECT * FROM public.user;" );
//
//
//        //update
//        PreparedStatement ps = connection.prepareStatement("UPDATE public.user SET name = ?  WHERE id = 9");
//        // set the preparedstatement parameters
//        ps.setString(1, "name_" + new Random(47).nextInt());
//        ps.executeUpdate();
//
//        //delete
//        sql = "DELETE from public.user where id = 9;";
//        stmt.executeUpdate(sql);
//        rs = stmt.executeQuery( "SELECT * FROM public.user;" );

//        DatabaseList databaseList = new DatabaseList();
//        databaseList.equalCommand();
//        contentsOfTheTables.getTableForView(connection, database);



//
//        rs.close();
//        stmt.close();
//        connection.close();


    }


    public void welcomeDatabase() throws SQLException, ClassNotFoundException {
        System.out.println("Hi, I'm Database manager! ");
        System.out.println("Please, write name of database in which you want to work, username and password in format: nameOfDataBase|username|password ");
       DatabaseManager databaseManager = new DatabaseManager();
       // Connection connection = databaseManager.connect("", "", "");

        UserConnection userConnection = new UserConnection(new ConsoleReader(), databaseManager, new ContentsOfTheTables());
       // ContentsOfTheTables contentsOfTheTables = new ContentsOfTheTables();
        userConnection.getConnectionData();


    }
}
