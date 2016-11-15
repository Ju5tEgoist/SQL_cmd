package com.company;


/**
 * Created by yulia on 04.10.16.
 */
public class ContentsOfTheTables{

    private int limit;
    private int offset;



    public String getLimitOffset(String[] tableNames, String selectedTableName, String result, String[] parts) {
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
    protected int getLimit(){ return limit;}

    public int getOffset(){
        return offset;
    }
}
