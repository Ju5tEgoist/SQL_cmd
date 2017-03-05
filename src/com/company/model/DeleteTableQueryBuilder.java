package com.company.model;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by yulia on 23.02.17.
 */
public class DeleteTableQueryBuilder {

    public boolean queryBuild(String tableName) throws SQLException {
        DeleteColumnDefinitionProvider deleteColumnDefinitionProvider = new DeleteColumnDefinitionProvider();
        List<InsertUpdateDeleteColumnDefinition> deleteColumnDefinition = deleteColumnDefinitionProvider.getProperties();
        String propertiesColumn = getColumnName(deleteColumnDefinition);
        String propertiesValue = getColumnValue(deleteColumnDefinition);
        String sql = "DELETE FROM public." + tableName + " " + "WHERE" + " " + propertiesColumn + " " + "=" + propertiesValue ;
        queryExecute(sql);
        return true;
    }

    private void queryExecute(String sql) throws SQLException {
        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.getStatement().executeUpdate(sql);
    }

    private String getColumnValue(List<InsertUpdateDeleteColumnDefinition> updateColumnDefinition) {
        String value = updateColumnDefinition.get(0).getValue();
        if(('a' <= value.charAt(0) && value.charAt(0) <= 'z') ||('A' <= value.charAt(0) && value.charAt(0) <= 'Z') ){
            value = "'" + value + "'";
        }
        return value;
    }

    private String getColumnName(List<InsertUpdateDeleteColumnDefinition> deleteColumnDefinition) {
        if(deleteColumnDefinition.get(0).getName() == null){
            throw new NoSuchElementException("Column does not exist");
        }
        return deleteColumnDefinition.get(0).getName();
    }
}
