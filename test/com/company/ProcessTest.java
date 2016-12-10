package com.company;

import com.company.Controller.*;
import com.company.Controller.Command.Change;
import com.company.Controller.Command.Command;
import com.company.Controller.Command.Find;
import com.company.model.DatabaseManager;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by yulia on 25.11.16.
 */
public class ProcessTest {
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
    public void shouldShowSelectedTableNameLO(){
        in.add("find");
        in.add("find user 1/1");
        String result = "find user 1/1";
        String[] parts = result.split(" ") ;
        String[] expectedTableNames = {"user", "employee"};
        ContentsOfTheTables contentsOfTheTables = new ContentsOfTheTables();
        assertEquals("user", contentsOfTheTables.getLimitOffset(expectedTableNames, result, parts));
    }
    @Test
    public void shouldShowSelectedTableName() throws SQLException {
        in.add("find");
        in.add("find user");
        String result = "find user";
        String[] parts = result.split(" ") ;
        String[] expectedTableNames = {"user", "employee"};
        Find find = new Find();
        assertEquals("user", find.getSelectedTableName(expectedTableNames, result, parts));
    }
    @Test
    public void shouldDelete() throws SQLException {
        in.add("sqlcmd|postgres|yes");
        Find.selectedTableName = "user";
        ChangeTable changeTable = new ChangeTable();
        assertEquals("name", changeTable.delete(1, "1"));
    }
    @Test
    public void shouldGetColumnName() throws SQLException {
        Find.selectedTableName = "user";
        ChangeTable changeTable = new ChangeTable();
        Statement stmt = DatabaseManager.getConnection().createStatement();
        assertEquals("name", changeTable.getColumnName(1, stmt));
    }
    @Test
    public void shouldGetAllColumnNames() throws SQLException {
        ChangeTable changeTable = new ChangeTable();
        Find.selectedTableName = "user";
        String columnsName = "(name,password,id)";
        Statement stmt = DatabaseManager.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM public." + Find.selectedTableName);
        assertEquals(columnsName, changeTable.getAllColumnNames(rs));
    }
    @Test
    public void shouldAddRow() throws SQLException {
        in.add("Tom manager");
        ChangeTable changeTable = new ChangeTable();
        Find.selectedTableName = "employee";
        assertTrue( changeTable.addRow());
    }
    @Test
    public void shouldRewrite() throws SQLException {
        in.add("Test");
        Find.selectedTableName = "user";
        ChangeTable changeTable = new ChangeTable();
        assertEquals("Test",changeTable.rewrite(1, "1"));
    }
    @Test
    public void isSelectedTableName() throws SQLException, ClassNotFoundException {
        Find.selectedTableName = null;
        in.add("user");
        String selectedTableName = "user";
        Change change = new Change();
        assertEquals(selectedTableName, change.isSelectedTableName("sqlcmd"));
    }
}
