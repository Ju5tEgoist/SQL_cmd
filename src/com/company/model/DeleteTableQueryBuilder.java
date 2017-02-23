package com.company.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by yulia on 23.02.17.
 */
public class DeleteTableQueryBuilder {
    public void queryBuilder(String tableName) throws SQLException {
        DeleteColumnDefinitionProvider deleteColumnDefinitionProvider = new DeleteColumnDefinitionProvider();
        List<InsertUpdateDeleteColumnDefinition> deleteColumnDefinition = deleteColumnDefinitionProvider.getProperties();
        String propertiesColumn = getColumnName(deleteColumnDefinition);
        String propertiesValue = getValue(deleteColumnDefinition);
        String sql = "DELETE FROM public." + tableName + " " + "WHERE" + " " + propertiesColumn + " " + "=" + propertiesValue ;
        DatabaseManager.getStatement().executeUpdate(sql);
    }

    private String getValue( List<InsertUpdateDeleteColumnDefinition> updateColumnDefinition) {
        String value = updateColumnDefinition.get(0).getValue();
        if(('a' <= value.charAt(0) && value.charAt(0) <= 'z') ||('A' <= value.charAt(0) && value.charAt(0) <= 'Z') ){
            value = "'" + value + "'";
        }
        return value;
    }

    private String getColumnName(List<InsertUpdateDeleteColumnDefinition> deleteColumnDefinition) {
        return deleteColumnDefinition.get(0).getName();
    }
}
