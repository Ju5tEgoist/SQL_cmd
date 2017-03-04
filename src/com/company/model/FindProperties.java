package com.company.model;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yulia on 04.10.16.
 */
public class FindProperties {

    static private int limit;
    static private int offset;

    public String getLimitOffset(String[] tableNames, String result, String[] parts) {
        String selectedTableName = null;
        if(parts.length == 3){
            String[] partsLO = parts[parts.length-1].split("/");
            String limitString = partsLO[0];
            String offsetString = partsLO[1];
            limit = Integer.parseInt(limitString);
            offset = Integer.parseInt(offsetString);
            for (String tableName : tableNames) {
                String expectedFirstCase = "find" + " " + tableName + " " + limitString + "/" + offsetString;
                if (result.equals(expectedFirstCase)) {
                    selectedTableName = tableName;
                    break;
                }
            }

        }
        return selectedTableName;
    }

    public int getLimit(){ return limit;}

    public int getOffset(){
        return offset;
    }


    public String[] getTableNames(String database) throws SQLException {
        String[] tableNames;
        String concatenatedTableNames = "";
        Connection connect = DatabaseManager.getConnection();
        System.out.println(database);
        ResultSet list = connect.getMetaData().getTables(database, "public", "%", null);
        while (list.next()) {
            String tableName = list.getString(3);
            concatenatedTableNames =  tableName  + " " + concatenatedTableNames;
        }
        tableNames = concatenatedTableNames.split(" ") ;

        return tableNames;
    }
}
