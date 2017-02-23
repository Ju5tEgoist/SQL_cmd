package com.company.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by yulia on 22.02.17.
 */
public class InsertTableQueryBuilder {
    public void queryBuilder(String tableName, ResultSet rs) throws SQLException {
        String propertiesColumn = "";
        String propertiesValue = "";
        InsertColumnDefinitionProvider insertColumnDefinitionProvider = new InsertColumnDefinitionProvider();
        List<InsertUpdateDeleteColumnDefinition> insertColumnDefinition = insertColumnDefinitionProvider.getProperties(rs);
        for (int i = 0; i < rs.getMetaData().getColumnCount(); i++){
            String value = insertColumnDefinition.get(i).getValue();
            if(('a' <= value.charAt(0) && value.charAt(0) <= 'z') ||('A' <= value.charAt(0) && value.charAt(0) <= 'Z') ){
                value = "'" + insertColumnDefinition.get(i).getValue() + "'";
            }
            if(i == 0){
                propertiesColumn += insertColumnDefinition.get(i).getName();
                propertiesValue += value;
            }
            else {
                propertiesColumn += "," + " " + insertColumnDefinition.get(i).getName();
                propertiesValue += "," + " " + value;
            }
        }
        String sql = "INSERT INTO public." + tableName + " " + "(" + propertiesColumn + ")" + " " + "VALUES" + " " + "(" + propertiesValue + ")";
        DatabaseManager.getStatement().executeUpdate(sql);
    }
}
