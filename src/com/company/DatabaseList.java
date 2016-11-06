package com.company;

import java.sql.*;

/**
 * Created by yulia on 02.10.16.
 */
public class DatabaseList{

    Connection connection;
    public DatabaseList(Connection connection){
        this.connection = connection;
    }
    ContentsOfTheTables contentsOfTheTables = new ContentsOfTheTables();

    public void getTableForView(String database) throws SQLException {

        String chosenTableName = contentsOfTheTables.getSelectedTableName();
        PreparedStatement ps;

        if(contentsOfTheTables.getLimit() != 0 && contentsOfTheTables.getOffset() != 0){
            String s = "SELECT * FROM public." + chosenTableName + " " + "LIMIT " + contentsOfTheTables.getLimit() + " " +  "OFFSET" + " " + contentsOfTheTables.getOffset();
            ps = connection.prepareStatement(s);
        }
        else {
            ps = connection.prepareStatement("select * from public." + chosenTableName);
        }

        ResultSet rs = ps.executeQuery();
        int columnCount = ps.getMetaData().getColumnCount();

        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(rs.getString(i) + "|");
            }
            System.out.print("\n");
        }
    }
}
