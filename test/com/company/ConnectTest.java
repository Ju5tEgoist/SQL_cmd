package com.company;

import com.company.Command.*;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.sql.SQLException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by yulia on 25.11.16.
 */
public class ConnectTest {
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
    @Test
    public void isConnected() throws SQLException {
        in.add("sqlcmd|postgres|yes");

        Connect connect = new Connect();
        assertTrue(connect.isResult());
    }
    @Test
    public void tablesList() throws SQLException {
        in.add("sqlcmd|postgres|yes");
        in.add("list");
        String[] expectedTableNames = {"user", "employee"};
        List list = new List();
        assertEquals(expectedTableNames, list.getTableNames("sqlcmd"));

    }
    @Test
    public void selectedTableNameLO(){
        in.add("find");
        in.add("find user 1/1");
        String result = "find user 1/1";
        String[] parts = result.split(" ") ;
        String[] expectedTableNames = {"user", "employee"};
        ContentsOfTheTables contentsOfTheTables = new ContentsOfTheTables();
        assertEquals("user", contentsOfTheTables.getLimitOffset(expectedTableNames, result, parts));
    }
    @Test
    public void selectedTableName() throws SQLException {
        in.add("find");
        in.add("find user");
        String result = "find user";
        String[] parts = result.split(" ") ;
        String[] expectedTableNames = {"user", "employee"};
        Find find = new Find();
        assertEquals("user", find.getSelectedTableName(expectedTableNames, result, parts));
    }
    @Test
    public void exit()  {
        in.add("exit");
        Exit exit = new Exit();
        assertTrue(exit.shouldProcess("exit"));
    }
    @Test
    public void find()  {
        in.add("find");
        Find find = new Find();
        assertTrue(find.shouldProcess("find"));
    }
    @Test
    public void list()  {
        in.add("list");
       List list = new List();
        assertTrue(list.shouldProcess("list"));
    }
    @Test
    public void connect()  {
        in.add("connect");
        Connect connect = new Connect();
        assertTrue(connect.shouldProcess("connect"));
    }
    @Test
    public void change()  {
        in.add("change");
        Change change = new Change();
        assertTrue(change.shouldProcess("change"));
    }
//    @Test
//    public void changeSelectedTableName() throws SQLException, ClassNotFoundException {
//
//        in.add("change");
//        in.add("change");
//        Find.selectedTableName = null;
//        in.add("user");
//        Change change = new Change();
//        assertEquals("user", change.isSelectedTableName("sqlcmd"));
//    }

}
