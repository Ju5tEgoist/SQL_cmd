package com.company.Command;

import com.company.ChangeTable;
import com.company.ConsoleReader;
import com.company.ContentsOfTheTables;

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
    Connection connection;
    ContentsOfTheTables contentsOfTheTables;
    ChangeTable changeTable;
    Change(Connection connection, ContentsOfTheTables contentsOfTheTables){
        this.connection = connection;
        this.contentsOfTheTables = contentsOfTheTables;
        this.changeTable = new ChangeTable(connection, contentsOfTheTables);
    }

    @Override
    public boolean isProcess(String command) {
        return command.startsWith("change ");
    }

    @Override
    public void process(String chosenTableName) throws SQLException {
            System.out.println("To change data in table enter column's and row's numbers in format: change <columnNumber|rowNumber>");
            String resultString = consoleReader.read();
            String[] parts = resultString.split(" ");

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
                process(chosenTableName);
            }

            Statement st = connection.createStatement();
            String result = "";
            ResultSet rs = st.executeQuery("SELECT * FROM public." + chosenTableName);
            int i = 1;
            while (rs.next()){
                if(i <= rowNumber){
                    result = rs.getString(columnNumber);
                    i++;
                }
            }
            System.out.println("The data which you want to change: " + result);
            changeTable.changeData(result, st, chosenTableName);
        }
    }

