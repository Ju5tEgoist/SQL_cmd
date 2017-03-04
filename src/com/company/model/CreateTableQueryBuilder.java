package com.company.model;

import java.sql.SQLException;

import java.util.List;

/**
 * Created by yulia on 21.02.17.
 */
public class CreateTableQueryBuilder {

    DatabaseManager databaseManager;

    public boolean queryBuildExecute(Integer columnNumber, String tableName) throws SQLException {
        databaseManager = new DatabaseManager();
        String properties = "";
        CreateColumnDefinitionProvider columnDefinitionProvider = new CreateColumnDefinitionProvider();
        List<CreateColumnDefinition> columnDefinition = columnDefinitionProvider.getProperties(columnNumber);
        properties = getString(properties, columnDefinition);
        String sql = "CREATE TABLE public." + tableName + "(" + properties + ")";
        databaseManager.getStatement().executeUpdate(sql);
        return true;
    }

    private String getString(String properties, List<CreateColumnDefinition> columnDefinition) {
        for (int i = 0; i < columnDefinition.size(); i++){
            if(i == 0){
                properties += columnDefinition.get(i).getName() + " " + columnDefinition.get(i).getDataType()
                        + " " + columnDefinition.get(i).getDefaultValue();
            }
            else {
                properties += "," + " " + columnDefinition.get(i).getName() + " "
                        + columnDefinition.get(i).getDataType() + " " + columnDefinition.get(i).getDefaultValue();
            }
        }
        return properties;
    }
}
