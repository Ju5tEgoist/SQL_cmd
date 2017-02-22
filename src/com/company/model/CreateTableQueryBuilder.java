package com.company.model;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by yulia on 21.02.17.
 */
public class CreateTableQueryBuilder {

    public void queryBuilder(Integer columnNumber, String tableName) throws SQLException {
        String properties = "";
        ColumnDefinitionProvider columnDefinitionProvider = new ColumnDefinitionProvider();
        List<ColumnDefinition> columnDefinition = columnDefinitionProvider.getProperties(columnNumber);
        int i = 0;
        while(i < columnDefinition.size()-1)  {
            properties += columnDefinition.iterator().next() + " ";
        }
        String sql = "CREATE TABLE public." + tableName + "(" + properties + ")";
        DatabaseManager.getStatement().executeUpdate(sql);
        System.out.println(sql);
    }


}
