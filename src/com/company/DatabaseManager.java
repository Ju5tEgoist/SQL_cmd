package com.company;

import java.sql.*;

/**
 * Created by yulia on 28.09.16.
 */
public class DatabaseManager {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");

        String database = "sqlcmd";
        String user = "postgres";
        String password = "yes";
        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/" + database, user,
                password);
        Statement stmt = connection.createStatement();
      //  ResultSet rs ;
        UserConnection userConnection = new UserConnection();


        userConnection.welcomeFromDatabase();
        if(userConnection.compareDataConnection()){
            System.out.println("Connection successfully");
        }
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
//
        ContentsOfTheTables contentsOfTheTables = new ContentsOfTheTables();
        System.out.println("If you want review all user's tables, please, enter command 'list'");
        DatabaseList databaseList = new DatabaseList();
        databaseList.equalCommand(database, connection);
        contentsOfTheTables.getTableForView( connection, database);



//
//        rs.close();
//        stmt.close();
//        connection.close();


    }
}
