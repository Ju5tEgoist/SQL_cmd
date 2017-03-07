package com.company;

import com.company.Controller.Command.ExitException;
import com.company.view.CustomInputStream;
import com.company.model.DatabaseManager;
import com.company.model.Main;
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
    boolean condition;
    @Before
    public void setup() {
        databaseManager = new DatabaseManager();
        out = new ByteArrayOutputStream();
        in = new CustomInputStream();

        System.setIn(in);
        System.setOut(new PrintStream(out));
        condition = true;
    }
//
    @Test(expected = ExitException.class)
    public void testHelp() {
        // given
        in.add("connect");
        in.add("sqlcmd|postgres|yes");
        in.add("help");
        in.add("exit");
        // when
        Main.main(new String[0]);
        // then
        assertEquals("Hi, I'm Database manager! \n" +
                "Enter the command, which you want to do or 'help'\n"+
                // connect
                "Please, write name of database in which you want to work, username and password in format: nameOfDataBase|username|password \n"+
                //sqlcmd|postgres|yes
                "Connection successful\n"+
                "Enter the command, which you want to do or 'help'.\n"+
                // command list
                "All available command: " +
                "\nhelp - show all command" +
                "\nconnect - connect to database" +
                "\nclear - clears the contents of the specified table" +
                "\ndrop - drops the specified table" +
                "\ncreate - create new table" +
                "\ninsert - add row in the table" +
                "\nupdate - change value" +
                "\ndelete - delete value" +
                "\nlist - to review all user's tables" +
                "\nfind - to find and view table in database" +
                "\nexit", getData());
        DatabaseManager.connection = null;

    }

    @Test(expected = ExitException.class)
    public void testFindTable() {
        // given
        in.add("connect");
        in.add("sqlcmd|postgres|yes");
        in.add("find");
        in.add("find first");
        in.add("exit");

        // when
        Main.main(new String[0]);

        // then
        assertEquals("Hi, I'm Database manager! \n"+
                        "To view all available command, enter: help or enter the command, which you want to do\n"+
                // connect
                        "Please, write name of database in which you want to work, username and password in format: nameOfDataBase|username|password \n" +
                        "Connection successful\n" +
                // find
                        "For view table, please, enter the name: find <tableName> or find <tableName LIMIT/OFFSET>\n"+
                // find first
                        "Serhio|26|\n" +
                        "Yuliia|21|\n"
                        , getData());
                //exit
        DatabaseManager.connection = null;
    }

    //TODO
    @Test(expected = ExitException.class)
    public void testFindTableLO() {
        // given
        in.add("connect");
        in.add("sqlcmd|postgres|yes");
        in.add("find");
        in.add("find first 1/1");
        in.add("exit");

        // when
        Main.main(new String[0]);

        // then
        assertEquals("Hi, I'm Database manager! \n"+
                "To view all available command, enter: help or enter the command, which you want to do\n"+
                // connect
                "Please, write name of database in which you want to work, username and password in format: nameOfDataBase|username|password \n" +
                // find
                "Connection successful\n" +
                "Enter the command, which you want to do or 'help'\n" +
                "For view table, please, enter the name: find <tableName> or find <tableName LIMIT/OFFSET>\n"+
                // find first
                "Arven|105|\n" +
                "Enter the command, which you want to do or 'help'"
                , getData());
        DatabaseManager.connection = null;
        //exit
    }

    @Test(expected = ExitException.class)
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
                "Please, write name of database in which you want to work, username and password in format: nameOfDataBase|username|password \n" +
                "Connection successful\n"
                , getData());

        //exit
        DatabaseManager.connection = null;
    }

    @Test(expected = ExitException.class)
    public void deleteTest() {
        // given
        in.add("connect");
        in.add("sqlcmd|postgres|yes");
        in.add("delete");
        in.add("first");
        in.add("age/21");
        in.add("exit");
        // when
        Main.main(new String[0]);
        // then
        assertEquals("Hi, I'm Database manager! \n"+
                "Enter the command, which you want to do or 'help'\n"+
                // connect
                "Please, write name of database in which you want to work, username and password in format: nameOfDataBase|username|password \n"+
                //sqlcmd|postgres|yes
                "Connection successful\n"+
                "Enter the command, which you want to do or 'help'.\n"+
                "Enter table name \n" +
                //change
                "This table: \n" +
                "name|age\n" +
                "Serhio|26|\n" +
                "Yuliia|21|\n" +
                "Now enter column's name and value which you want delete: <name/value>\n"+
                //change 1|1
                "Serhio|26|\n" +
                "Enter the command, which you want to do or 'help'\n"
                , getData());
        DatabaseManager.connection = null;
        //exit
    }

    @Test(expected = ExitException.class)
    public void insertTest() {
        // given
        in.add("connect");
        in.add("sqlcmd|postgres|yes");
        in.add("insert");
        in.add("first");
        in.add("name/Arven");
        in.add("age/105");
        in.add("exit");
        // when
        Main.main(new String[0]);
        // then
        assertEquals("Hi, I'm Database manager! \n"+
                "Enter the command, which you want to do or 'help'\n"+
                // connect
                "Please, write name of database in which you want to work, username and password in format: nameOfDataBase|username|password \n"+
                //sqlcmd|postgres|yes
                "Connection successful\n"+
                "Enter the command, which you want to do or 'help'.\n"+
                "Enter table name \n" +
                //change
                "This table: \n" +
                "name|age\n" +
                "Serhio|26|\n" +
                "Yuliia|21|\n" +
                "\"Now enter column's name and value: <name/value>\"\n"+
                //change 1|1
                "Your data were added\n" +
                "Enter the command, which you want to do or 'help'\n"
                , getData());
        DatabaseManager.connection = null;
        //exit
    }

    @Test(expected = ExitException.class)
    public void updateTest() {
        // given
        in.add("connect");
        in.add("sqlcmd|postgres|yes");
        in.add("update");
        in.add("first");
        in.add("1");
        in.add("name/Yulia");
        in.add("age/21");
        in.add("exit");
        // when
        Main.main(new String[0]);
        // then
        assertEquals("Hi, I'm Database manager! \n"+
                "Enter the command, which you want to do or 'help'\n"+
                // connect
                "Please, write name of database in which you want to work, username and password in format: nameOfDataBase|username|password \n"+
                //sqlcmd|postgres|yes
                "Connection successful\n"+
                "Enter the command, which you want to do or 'help'.\n"+
                "Enter table name \n" +
                "This table: \n" +
                "name|age\n" +
                "Serhio|26|\n" +
                "Yuliia|21|\n" +
                "Now enter the column number in which you want change value\n"+
                "Now enter column's name and value: <name/value>. For column which changes type new value\n" +
                "Now enter column's name and value: <name/value>. For column which changes type new value\n" +
                "Serhio|26|\n" +
                "Yulia|21|\n" +
                "Enter the command, which you want to do or 'help'\n"
                , getData());
        DatabaseManager.connection = null;
        //exit
    }
    @Test(expected = ExitException.class)
    public void createTest() {
        // given
        in.add("connect");
        in.add("sqlcmd|postgres|yes");
        in.add("create");
        in.add("Hi");
        in.add("2");
        in.add("Done/text");
        in.add("WillDo/text");
        in.add("exit");
        // when
        Main.main(new String[0]);
        // then
        assertEquals("Hi, I'm Database manager! \n"+
                "Enter the command, which you want to do or 'help'\n"+
                // connect
                "Please, write name of database in which you want to work, username and password in format: nameOfDataBase|username|password \n"+
                //sqlcmd|postgres|yes
                "Connection successful\n" +
                "Enter the command, which you want to do or 'help'.\n"+
                "Enter table name \n" +

                "Please, type the number of columns\n" +
                "Now enter column's name and type of this column: <name/type>\n"+
                "Now enter column's name and type of this column: <name/type>\n" +
                "Hi was created\n" +
                "Enter the command, which you want to do or 'help'\n"
                , getData());
        DatabaseManager.connection = null;
        //exit
    }

    @Test(expected = ExitException.class)
    public void dropTest() {
        // given
        in.add("connect");
        in.add("sqlcmd|postgres|yes");
        in.add("drop");
        in.add("Hello");
        in.add("exit");
        // when
        Main.main(new String[0]);
        // then
        assertEquals("Hi, I'm Database manager! \n"+
                "Enter the command, which you want to do or 'help'\n"+
                // connect
                "Please, write name of database in which you want to work, username and password in format: nameOfDataBase|username|password \n"+
                //sqlcmd|postgres|yes
                "Connection successful\n" +
                "Enter the command, which you want to do or 'help'.\n"+
                "Enter table name \n" +
                "Hello is dropped\n" +
                "Enter the command, which you want to do or 'help'\n"
                , getData());
        DatabaseManager.connection = null;
        //exit
    }

    @Test(expected = ExitException.class)
    public void clearTest() {
        // given
        in.add("connect");
        in.add("sqlcmd|postgres|yes");
        in.add("clear");
        in.add("employee");
        in.add("1");
        in.add("name/Yulia");
        in.add("age/21");
        in.add("exit");
        // when
        Main.main(new String[0]);
        // then
        assertEquals("Hi, I'm Database manager! \n"+
                "Enter the command, which you want to do or 'help'\n"+
                // connect
                "Please, write name of database in which you want to work, username and password in format: nameOfDataBase|username|password \n"+
                //sqlcmd|postgres|yes
                "Connection successful\n"+
                "Enter the command, which you want to do or 'help'.\n"+
                "Enter table name \n" +
                "employee was cleaned\n" +
                "Enter the command, which you want to do or 'help'\n"
                , getData());
        DatabaseManager.connection = null;
        //exit
    }

    @Test(expected = ExitException.class)
    public void listTest() {
        // given
        in.add("connect");
        in.add("sqlcmd|postgres|yes");
        in.add("list");
        in.add("employee");
        in.add("1");
        in.add("name/Yulia");
        in.add("age/21");
        in.add("exit");
        // when
        Main.main(new String[0]);
        // then
        assertEquals("Hi, I'm Database manager! \n"+
                "Enter the command, which you want to do or 'help'\n"+
                // connect
                "Please, write name of database in which you want to work, username and password in format: nameOfDataBase|username|password \n"+
                //sqlcmd|postgres|yes
                "Connection successful\n"+
                "Enter the command, which you want to do or 'help'.\n"+
                "List of all available tables: [employee, first, hi, test, testr, today, todaynew, todaynewsmth, tt]" +
                "Enter the command, which you want to do or 'help'\n"
                , getData());
        //exit
        DatabaseManager.connection = null;
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
