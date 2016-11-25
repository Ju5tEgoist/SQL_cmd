package com.company;

import com.company.CustomInputStream;

import com.company.Main;
import org.junit.Before;
import org.junit.Test;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;


import static org.junit.Assert.assertEquals;


/**
 * Created by yulia on 21.11.16.
 */
public class IntegrationTest {
    private CustomInputStream in;
    private ByteArrayOutputStream out;
    private DatabaseManager databaseManager;

    @Before
    public void setup() {
        databaseManager = new DatabaseManager();
        out = new ByteArrayOutputStream();
        in = new CustomInputStream();

        System.setIn(in);
        System.setOut(new PrintStream(out));
    }
//
    @Test
    public void testCommandList() {
        // given
        in.add("command list");
        in.add("exit");

        // when
        Main.main(new String[0]);

        // then
        assertEquals("Hi, I'm Database manager! \n" +
                "To view all available command, enter: command list or enter the command, which you want to do\n" +
                // command list
                "All available command: " +
                "\nconnect - connect to database" +
                "\nlist - to review all user's tables" +
                "\nfind <tableName> - to find and view table in database" +
                "\nfind <tableName Limit/Offset> - to find and view part of table in database" +
                "\nchange <columnNumber|rowNumber> - to change data in the table" +
                "\ninsert - to add new rows of data to a table in the database" +
                "\nselect - to select data from the database" +
                "\ndelete - to delete records in the table" +
                "\nupdate - to update records in a table" +
                "\nexit\n", getData());

    }
    @Test
    public void testFindTable() {
        // given
        in.add("connect");
        in.add("sqlcmd|postgres|yes");
        in.add("find");
        in.add("find user");
        in.add("exit");

        // when
        Main.main(new String[0]);

        // then
        assertEquals("Hi, I'm Database manager! \n"+
                        "To view all available command, enter: command list or enter the command, which you want to do\n"+
                // connect
                        "Please, write name of database in which you want to work, username and password in format: nameOfDataBase|username|password \n" +
                //sqlcmd|postgres|yes
          //              "Connection successful\n"+
                // find

                        "For view table, please, enter the name: find <tableName> or find <tableName LIMIT/OFFSET>\n"+
                // find user
                        "Rick|999080|9|\n" +
                        "John|580|6|\n" +
                        "Patrik|null|3|\n" +
                        "Mike|333|16|\n" +
                        "Harry|pass|1|\n" +
                        "Bob|123|7|\n" +
                        "Nick|24|98|\n" +
                        "Martin|58|17|\n", getData());
                //exit
    }

    @Test
    public void connectTest() {
        // given
        in.add("connect");
        in.add("sqlcmd|postgres|yes");
        in.add("exit");
        // when
        Main.main(new String[0]);

        // then
        assertEquals("Hi, I'm Database manager! \n"+
                "To view all available command, enter: command list or enter the command, which you want to do\n"+
                // connect
                "Please, write name of database in which you want to work, username and password in format: nameOfDataBase|username|password \n"
               , getData());

        //exit
    }

    @Test
    public void changeRewriteTest() {
        // given
        in.add("connect");
        in.add("sqlcmd|postgres|yes");

        in.add("change");
        in.add("user");
        in.add("change 1|1");
        in.add("rewrite");
        in.add("Test");
        in.add("exit");
        // when
        Main.main(new String[0]);

        // then
        assertEquals("Hi, I'm Database manager! \n"+
                "To view all available command, enter: command list or enter the command, which you want to do\n"+
                // connect
                "Please, write name of database in which you want to work, username and password in format: nameOfDataBase|username|password \n"+
                //sqlcmd|postgres|yes

                "Before change table, please, enter table name.\n"+
                "List of all available tables: [employee, user]\t\n"+
                //change
                "To change data in table enter column's and row's numbers in format: change <columnNumber|rowNumber>\n"+
                //change 1|1
                "The data which you want to change: Rick\n"+
                "Put the name of the action which you want to do with data: addRow, delete, rewrite\n"+
                //rewrite
                "Enter new data\n"
                //Test


                , getData());


        //exit
    }
    @Test
    public void changeDeleteTest() {
        // given
        in.add("connect");
        in.add("sqlcmd|postgres|yes");

        in.add("change");
        in.add("user");
        in.add("change 1|1");
        in.add("delete");

        in.add("exit");
        // when
        Main.main(new String[0]);

        // then
        assertEquals("Hi, I'm Database manager! \n"+
                "To view all available command, enter: command list or enter the command, which you want to do\n"+
                // connect
                "Please, write name of database in which you want to work, username and password in format: nameOfDataBase|username|password \n"+
                //sqlcmd|postgres|yes
                "Connection successful\n"+
                "Before change table, please, enter table name.\n"+
                "List of all available tables: [employee, user]\t\n"+
                //change
                "To change data in table enter column's and row's numbers in format: change <columnNumber|rowNumber>\n"+
                //change 1|1
                "The data which you want to change: Rick\n"+
                "Put the name of the action which you want to do with data: addRow, delete, rewrite\n"
                , getData());


        //exit
    }

    @Test
    public void changeAddRowTest() {
        // given
        in.add("connect");
        in.add("sqlcmd|postgres|yes");

        in.add("change");
        in.add("user");
        in.add("change 1|1");
        in.add("addRow");
        in.add("Test test 00");
        in.add("exit");
        // when
        Main.main(new String[0]);

        // then
        assertEquals("Hi, I'm Database manager! \n"+
                "To view all available command, enter: command list or enter the command, which you want to do\n"+
                // connect
                "Please, write name of database in which you want to work, username and password in format: nameOfDataBase|username|password \n"+
                //sqlcmd|postgres|yes

                "Before change table, please, enter table name.\n"+
                "List of all available tables: [employee, user]\t\n"+
                //change
                "To change data in table enter column's and row's numbers in format: change <columnNumber|rowNumber>\n"+
                //change 1|1
                "The data which you want to change: Rick\n"+
                "Put the name of the action which you want to do with data: addRow, delete, rewrite\n"+
                "Enter new data separated by space\n"
                , getData());


        //exit
    }
    @Test
    public void changeErrorTest() {
        // given
        in.add("connect");
        in.add("sqlcmd|postgres|yes");

        in.add("change");
        in.add("user");
        in.add("change 1|1");
        in.add("uuuuu");

        in.add("exit");
        // when
        Main.main(new String[0]);

        // then
        assertEquals("Hi, I'm Database manager! \n"+
                "To view all available command, enter: command list or enter the command, which you want to do\n"+
                // connect
                "Please, write name of database in which you want to work, username and password in format: nameOfDataBase|username|password \n"+
                //sqlcmd|postgres|yes

                "Before change table, please, enter table name.\n"+
                "List of all available tables: [employee, user]\t\n"+
                //change
                "To change data in table enter column's and row's numbers in format: change <columnNumber|rowNumber>\n"+
                //change 1|1
                "The data which you want to change: Rick\n"+
                "Put the name of the action which you want to do with data: addRow, delete, rewrite\n"+
                "This action does not exist\n"+
                "Put the name of the action which you want to do with data: addRow, delete, rewrite\n"+
                "This action does not exist\n"+
                "Put the name of the action which you want to do with data: addRow, delete, rewrite\n"
                , getData());


        //exit
    }
    public String getData() {
        try {
            String result = new String(out.toByteArray(), "UTF-8");
            out.reset();
            return result;
        } catch (UnsupportedEncodingException e) {
            return e.getMessage();
        }
    }
}
