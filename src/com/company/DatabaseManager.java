package com.company;

import java.sql.*;

/**
 * Created by yulia on 28.09.16.
 */
public class DatabaseManager {
    private static ConsoleReader consoleReader;
    static Connection connection;
    static String database;



    public void connect(String database, String user, String password) throws SQLException, ClassNotFoundException {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Please add jdbc jar to project.", e);
            }
            try {
                connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/" + database, user,
                        password);
            } catch (SQLException e) {
                connection = null;
                throw new RuntimeException(
                        String.format("Cant get connection for model:%s user:%s",
                                database, user),
                        e);
            }
    }


    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        UserConnection userConnection = new UserConnection(consoleReader);
        ContentsOfTheTables contentsOfTheTables = new ContentsOfTheTables();
        if( userConnection.welcomeFromDatabase()){
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
        System.out.println("If you want review all user's tables, please, enter command 'list'");
        DatabaseList databaseList = new DatabaseList();
        databaseList.equalCommand();
//        DatabaseManager db = new DatabaseManager(connection, database);
        contentsOfTheTables.getTableForView(connection, database);



//
//        rs.close();
//        stmt.close();
//        connection.close();


    }

}
