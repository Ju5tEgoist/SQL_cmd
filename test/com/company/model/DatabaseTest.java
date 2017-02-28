package com.company.model;

import com.company.Controller.Command.Connect;
import com.company.Controller.Command.List;
import com.company.view.CustomInputStream;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by yulia on 01.12.16.
 */
public class DatabaseTest {
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
    public void shouldConnected() throws SQLException {
        in.add("sqlcmd|postgres|yes");

        Connect connect = new Connect();
        assertTrue(connect.isResult());
    }
//    @Test
//    public void shouldShowTablesList() throws SQLException {
//        in.add("sqlcmd|postgres|yes");
//        in.add("list");
//        String[] expectedTableNames = {"user", "employee"};
//        List list = new List();
//        assertEquals(expectedTableNames, list.getTableNames("sqlcmd"));
//
//    }

}
