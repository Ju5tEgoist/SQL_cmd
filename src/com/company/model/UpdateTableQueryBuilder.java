package com.company.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by yulia on 23.02.17.
 */
public class UpdateTableQueryBuilder {
    public void queryBuilder(String tableName, ResultSet rs, int columnNumber) throws SQLException {
        UpdateColumnDefinitionProvider updateColumnDefinitionProvider = new UpdateColumnDefinitionProvider();
        List<InsertUpdateDeleteColumnDefinition> updateColumnDefinition = updateColumnDefinitionProvider.getProperties(rs);
        String propertiesColumn = getColumnName(updateColumnDefinition, columnNumber);
        String propertiesNeighbourColumn = getColumnName(updateColumnDefinition, getNeighborColumnNumber(columnNumber));
        String propertiesValue = getValue(updateColumnDefinition, columnNumber);
        String propertiesNeighbourValue = getValue(updateColumnDefinition, getNeighborColumnNumber(columnNumber));
        String sql = "UPDATE public." + tableName + " " + "SET" + " " + propertiesColumn + " " + "=" + " " +
                propertiesValue + " " + "WHERE" + " " + propertiesNeighbourColumn + " " + "=" + propertiesNeighbourValue ;
        DatabaseManager.getStatement().executeUpdate(sql);
    }

    private String getValue( List<InsertUpdateDeleteColumnDefinition> updateColumnDefinition, int columnNumber) {
        String value = updateColumnDefinition.get(columnNumber-1).getValue();
        if(('a' <= value.charAt(0) && value.charAt(0) <= 'z') ||('A' <= value.charAt(0) && value.charAt(0) <= 'Z') ){
            value = "'" + value + "'";
        }
        return value;
    }

    private String getColumnName(List<InsertUpdateDeleteColumnDefinition> updateColumnDefinition, int columnNumber) {
        return updateColumnDefinition.get(columnNumber-1).getName();
    }

    private int getNeighborColumnNumber(int columnNumber){
        return columnNumber - 1 >= 1 ? columnNumber - 1 :  columnNumber + 1;
    }

}
