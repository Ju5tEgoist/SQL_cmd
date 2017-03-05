package com.company.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by yulia on 22.02.17.
 */
public class InsertTableQueryBuilder {

    public boolean queryBuilderExecute(String tableName, ResultSet rs) throws SQLException {
        InsertColumnDefinitionProvider insertColumnDefinitionProvider = new InsertColumnDefinitionProvider();
        List<InsertUpdateDeleteColumnDefinition> insertColumnDefinition = insertColumnDefinitionProvider.getProperties(rs);
        String propertiesValue = getPropertiesValue(rs, insertColumnDefinition);
        String propertiesColumn = getPropertiesColumn(rs, insertColumnDefinition);
        String sql = "INSERT INTO public." + tableName + " " + "(" + propertiesColumn + ")" + " " + "VALUES" + " " + "(" + propertiesValue + ")";
        queryExecute(sql);
        return true;
    }

    private void queryExecute(String sql) throws SQLException {
        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.getStatement().executeUpdate(sql);
    }

    private String getPropertiesValue(ResultSet rs, List<InsertUpdateDeleteColumnDefinition> insertColumnDefinition) throws SQLException {
        String propertiesValue = "";
        for (int i = 0; i < rs.getMetaData().getColumnCount(); i++){
            String value = insertColumnDefinition.get(i).getValue();
            if(('a' <= value.charAt(0) && value.charAt(0) <= 'z') ||('A' <= value.charAt(0) && value.charAt(0) <= 'Z') ){
                value = "'" + insertColumnDefinition.get(i).getValue() + "'";
            }
            if(i == 0){
                propertiesValue += value;
            }
            else {
                propertiesValue += "," + " " + value;
            }
        }
        return propertiesValue;
    }

    private String getPropertiesColumn(ResultSet rs, List<InsertUpdateDeleteColumnDefinition> insertColumnDefinition) throws SQLException {
        String propertiesColumn = "";
        for (int i = 0; i < rs.getMetaData().getColumnCount(); i++){
            if(i == 0){
                propertiesColumn += insertColumnDefinition.get(i).getName();
            }
            else {
                propertiesColumn += "," + " " + insertColumnDefinition.get(i).getName();
            }
        }
        return propertiesColumn;
    }
}
