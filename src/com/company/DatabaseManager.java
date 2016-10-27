package com.company;

import java.sql.*;

/**
 * Created by yulia on 28.09.16.
 */
public class DatabaseManager {
    private static ConsoleReader consoleReader;
    static String database;
    Connection connection;


    public Connection connect(String database, String user, String password) throws SQLException, ClassNotFoundException {
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
                throw new RuntimeException(
                        String.format("Can't get connection for model:%s user:%s",
                                database, user),
                        e);

            }
        System.out.println("Connection successful");
        return connection;
    }


    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        UserConnection userConnection = new UserConnection();
        ContentsOfTheTables contentsOfTheTables = new ContentsOfTheTables();
         userConnection.welcomeDatabase();

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
//        contentsOfTheTables.getTableForView(c, database);



//
//        rs.close();
//        stmt.close();
//        connection.close();


    }

}
