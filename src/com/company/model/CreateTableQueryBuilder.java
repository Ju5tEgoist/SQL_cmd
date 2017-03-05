package com.company.model;

import java.sql.SQLException;

import java.util.List;

/**
 * Created by yulia on 21.02.17.
 */
public class CreateTableQueryBuilder {

    public boolean queryBuild(Integer columnNumber, String tableName) throws SQLException {

        String properties = "";
        CreateColumnDefinitionPropertiesProvider columnDefinitionProvider = new CreateColumnDefinitionPropertiesProvider();
        List<CreateColumnDefinition> columnDefinition = columnDefinitionProvider.getProperties(columnNumber);
        properties = getProperties(properties, columnDefinition);
        String sql = "CREATE TABLE public." + tableName + "(" + properties + ")";
        queryExecute(sql);
        return true;
    }

    private void queryExecute(String sql) throws SQLException {
        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.getStatement().executeUpdate(sql);
    }

    private String getProperties(String properties, List<CreateColumnDefinition> columnDefinition) {
        for (int i = 0; i < columnDefinition.size(); i++){
            properties +=  i > 0 ?  "," + " " + columnDefinition.get(i).getName() + " "
                 + columnDefinition.get(i).getDataType() + " " + columnDefinition.get(i).getDefaultValue() :
                 columnDefinition.get(i).getName() + " " + columnDefinition.get(i).getDataType()
                            + " " + columnDefinition.get(i).getDefaultValue();
        }
        return properties;
    }
}
