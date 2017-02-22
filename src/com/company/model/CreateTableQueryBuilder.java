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
        for (int i = 0; i < columnDefinition.size(); i++){
            if(i == 0){
                properties += columnDefinition.get(i).getName() + " " + columnDefinition.get(i).getDataType() + " " + columnDefinition.get(i).getDefaultValue();
            }
            else {
                properties += "," + " " + columnDefinition.get(i).getName() + " " + columnDefinition.get(i).getDataType() + " " + columnDefinition.get(i).getDefaultValue();
            }
        }
        String sql = "CREATE TABLE public." + tableName + "(" + properties + ")";
        System.out.println(sql);
        DatabaseManager.getStatement().executeUpdate(sql);
    }


}
