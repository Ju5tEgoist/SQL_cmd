package com.company.model;


import com.company.view.TablePresenter;

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
        ResultSet list = connect.getMetaData().getTables(database, "public", "%", null);
        while (list.next()) {
            String tableName = list.getString(3);
            concatenatedTableNames =  tableName  + " " + concatenatedTableNames;
        }
        tableNames = concatenatedTableNames.split(" ") ;

        return tableNames;
    }

    public String getSelectedTableName(String[] tableNames, String result, String[] parts) throws SQLException {
        String selectedTableName = null;
        FindProperties findProperties = new FindProperties();
        TablePresenter tablePresenter = new TablePresenter();
        for (String tableName : tableNames) {
            String expectedFirstCase = "find" + " " + tableName;
            if (result.equals(expectedFirstCase)) {
                selectedTableName = tableName;
                break;
            }

        }
        if (selectedTableName == null) {
            selectedTableName = findProperties.getLimitOffset(tableNames, result, parts);
            if (selectedTableName == null) {
                System.out.println("Can not find table with this name. Try again");
            } else {
                tablePresenter.showTable(selectedTableName);
            }
        } else {
            tablePresenter.showTable(selectedTableName);
        }
        return selectedTableName;
    }
}
