package com.company.view;

import com.company.model.DatabaseManager;
import com.company.model.FindProperties;

import java.sql.*;

/**
 * Created by yulia on 02.03.17.
 */
public class TablePresenter {

    public void showTable(String selectedTableName) throws SQLException {
        FindProperties findProperties = new FindProperties();
        Connection connection = DatabaseManager.getConnection();
        PreparedStatement ps;

        if(findProperties.getLimit() != 0 && findProperties.getOffset() != 0){
            String s = "SELECT * FROM " + selectedTableName + " " + "LIMIT " + findProperties.getLimit() + " " +  "OFFSET" + " " + findProperties.getOffset();
            ps = connection.prepareStatement(s);
        }
        else {
            ps = connection.prepareStatement("select * from " + selectedTableName);
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

    public void showColumns(String tableName) throws SQLException {
        System.out.println("This table: ");
        ResultSet rs = new DatabaseManager().getStatement().executeQuery("SELECT * FROM " + tableName);
        ResultSetMetaData metaData = rs.getMetaData();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            System.out.print(i > 1 ? "|" + metaData.getColumnName(i) : metaData.getColumnName(i));
        }
        System.out.println("\n");
    }
}
