package com.company.Command;

import com.company.ChangeTable;
import com.company.ConsoleReader;
import com.company.ContentsOfTheTables;
import com.company.DatabaseManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by yulia on 06.11.16.
 */
public class Change implements Command {
    ConsoleReader consoleReader = new ConsoleReader();
    private int columnNumber;
    private int rowNumber;
    ContentsOfTheTables contentsOfTheTables = new ContentsOfTheTables();
    ChangeTable changeTable = new ChangeTable();
    List list = new List();

    @Override
    public boolean shouldProcess(String command) {
        return command != null && command.startsWith("change");
    }

    @Override
    public void process(String database) throws SQLException, ClassNotFoundException {
        String selectedTableName = isSelectedTableName(database);
        System.out.println("To change data in table enter column's and row's numbers in format: change <columnNumber|rowNumber>");
        String resultString = consoleReader.read();
        String[] parts = resultString.split(" ");
        checkCommand(selectedTableName, parts);
        Connection connection = DatabaseManager.getConnection();
        Statement st = connection.createStatement();
        String result = "";
        String id = null;
        ResultSet rs = st.executeQuery("SELECT * FROM public." + selectedTableName);
        int i = 1;
            while (rs.next()){
                if(i <= rowNumber){
                    result = rs.getString(columnNumber);
                    id = rs.getString(3);
                    i++;
                }
            }
        System.out.println("The data which you want to change: " + result);
        changeTable.changeData(id, columnNumber);
        }

    private void checkCommand(String selectedTableName, String[] parts) throws SQLException, ClassNotFoundException {
        if (parts.length == 2) {
            String[] partsCR = parts[parts.length - 1].split("\\|");
            String limitString = partsCR[0];
            String offsetString = partsCR[1];
            columnNumber = Integer.parseInt(limitString);
            rowNumber = Integer.parseInt(offsetString);
            int offset = contentsOfTheTables.getOffset();
            if(offset != 0){
                rowNumber = rowNumber + offset;
            }
        }
        else {
            System.out.println("This command does not exist. Try again");
            process(selectedTableName);
        }
    }

    private String isSelectedTableName(String database) throws SQLException, ClassNotFoundException {
        String selectedTableName = Find.selectedTableName;
        if(selectedTableName == null){
            System.out.println("Before change table, please, enter table name.");
            list.process(database);
            String[] tableNames = list.getTableNames(database);
           System.out.println("\t");
            String inputName = consoleReader.read();
            for(String tableName : tableNames){
                if(tableName.equals(inputName)){
                    selectedTableName = tableName;
                }
            }
            if(selectedTableName == null){
                System.out.println("Try again");
                isSelectedTableName(database);

            }
        }
        return selectedTableName;
    }
}

