package com.company.model;

import com.company.Controller.ContentsOfTheTables;

import java.sql.*;

/**
 * Created by yulia on 02.10.16.
 */
public class DatabaseList{

    public void getTableForView(String selectedTableName) throws SQLException {
        ContentsOfTheTables contentsOfTheTables = new ContentsOfTheTables();
        Connection connection = DatabaseManager.getConnection();
        PreparedStatement ps;

        if(contentsOfTheTables.getLimit() != 0 && contentsOfTheTables.getOffset() != 0){
            String s = "SELECT * FROM public." + selectedTableName + " " + "LIMIT " + contentsOfTheTables.getLimit() + " " +  "OFFSET" + " " + contentsOfTheTables.getOffset();
            ps = connection.prepareStatement(s);
        }
        else {
            ps = connection.prepareStatement("select * from public." + selectedTableName);
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
