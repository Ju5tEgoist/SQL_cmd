package com.company;

import com.company.Controller.*;
import com.company.Controller.Command.Delete;
import com.company.Controller.Command.Find;
import com.company.model.*;
import com.company.view.CustomInputStream;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by yulia on 25.11.16.
 */
public class ProcessTest {
    public static final String TABLE_NAME = "testTable";
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
    @After
    public void cleanUp() throws SQLException, ClassNotFoundException {
        DatabaseManager.connect("sqlcmd", "postgres", "yes");
        DatabaseManager.getStatement().executeUpdate("DROP TABLE IF EXISTS public." + TABLE_NAME);
    }
    @Test
    public void shouldConnect() throws SQLException, ClassNotFoundException {
        assertNotNull(DatabaseManager.connect("sqlcmd", "postgres", "yes"));
    }
    @Test
    public void shouldShowTableNameLimitOffset(){
        in.add("find");
        in.add("find first 1/1");
        String result = "find first 1/1";
        String[] parts = result.split(" ") ;
        String[] expectedTableNames = {"employee", "first", "hi", "test", "testr", "today", "todaynew", "todaynewsmth", "tt"};
        FindProperties findProperties = new FindProperties();
        assertEquals("first", findProperties.getLimitOffset(expectedTableNames, result, parts));
    }
    @Test
    public void shouldShowSelectedTableName() throws SQLException {
        in.add("sqlcmd|postgres|yes");
        in.add("find");
        in.add("find first");
        String result = "find first";
        String[] parts = result.split(" ") ;
        String[] expectedTableNames = {"employee", "first", "hi", "test", "testr", "today", "todaynew", "todaynewsmth", "tt"};
        FindProvider findProvider = new FindProvider();
        assertEquals("first", findProvider.getSelectedTableName(expectedTableNames, result, parts, "first"));
    }

    @Test
    public void shouldGetTableNames() throws SQLException {
        in.add("sqlcmd|postgres|yes");
        in.add("find");
        in.add("find first");
        String[] expectedTableNames = {"tt",  "todaynewsmth", "today", "testr", "test", "hi", "first", "employee"};
        FindProperties findProperties = new FindProperties();
        assertArrayEquals(expectedTableNames, findProperties.getTableNames("sqlcmd"));
    }

    @Test
    public void shouldBuildExecuteQueryCreate() throws SQLException {
        in.add("name/text");
        Statement statement = DatabaseManager.getConnection().createStatement();
        CreateTableQueryBuilder createTableQueryBuilder = new CreateTableQueryBuilder();
        assertTrue(createTableQueryBuilder.queryBuildExecute(1, TABLE_NAME));
    }
    @Test
    public void shouldNotNullGetPropertiesInsert() throws SQLException {
        DatabaseManager.connection = null;
        in.add("sqlcmd|postgres|yes");
        in.add("name/Test");
        in.add("age/0");
        Statement statement = DatabaseManager.getConnection().createStatement();
        ResultSet rs =  statement.executeQuery("SELECT * FROM public." + "first");
        List<InsertUpdateDeleteColumnDefinition> insertColumnDefinitions = new ArrayList<>();
        for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
            InsertUpdateDeleteColumnDefinition insertColumnDefinition = InsertUpdateDeleteColumnDefinition.builder()
                    .name("name")
                    .value("Test")
                    .build();
            insertColumnDefinitions.add(insertColumnDefinition);
        }
        InsertColumnDefinitionProvider insertColumnDefinitionProvider = new InsertColumnDefinitionProvider();
        assertTrue(EqualsBuilder.reflectionEquals(insertColumnDefinitions, insertColumnDefinitionProvider.getProperties(rs)));
    }
    @Test
    public void shouldNotNullGetPropertiesCreate() throws SQLException {
        in.add("name/text");
        List<CreateColumnDefinition> createColumnDefinitions = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            CreateColumnDefinition createColumnDefinition = CreateColumnDefinition.builder()
                    .name("name")
                    .nullable(false)
                    .dataType("text")
                    .build();
            createColumnDefinitions.add(createColumnDefinition);
        }
        CreateColumnDefinitionProvider createColumnDefinitionProvider = new CreateColumnDefinitionProvider();
        assertTrue(EqualsBuilder.reflectionEquals(createColumnDefinitions, createColumnDefinitionProvider.getProperties(1)));
    }
    @Test
    public void shouldBuildExecuteQueryInsert() throws SQLException {
        DatabaseManager.connection = null;
        in.add("sqlcmd|postgres|yes");
        in.add("name/Test");
        in.add("age/10");
        Statement statement = DatabaseManager.getConnection().createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM public." + "first");
        InsertTableQueryBuilder insertTableQueryBuilder = new InsertTableQueryBuilder();
        assertTrue(insertTableQueryBuilder.queryBuilder("first", rs));
    }
//    @Test
//    public void shouldGetAllColumnNames() throws SQLException {
//        ChangeTable changeTable = new ChangeTable();
//        Find.selectedTableName = "user";
//        String columnsName = "(name,password,id)";
//        Statement stmt = DatabaseManager.getConnection().createStatement();
//        ResultSet rs = stmt.executeQuery("SELECT * FROM public." + Find.selectedTableName);
//        assertEquals(columnsName, changeTable.getAllColumnNames(rs));
//    }
//    @Test
//    public void shouldAddRow() throws SQLException {
//        in.add("Tom manager");
//        ChangeTable changeTable = new ChangeTable();
//        Find.selectedTableName = "employee";
//        assertTrue( changeTable.addRow());
//    }
//    @Test
//    public void shouldRewrite() throws SQLException {
//        in.add("Test");
//        Find.selectedTableName = "user";
//        ChangeTable changeTable = new ChangeTable();
//        assertEquals("Test",changeTable.rewrite(1, "1"));
//    }
//    @Test
//    public void isSelectedTableName() throws SQLException, ClassNotFoundException {
//        Find.selectedTableName = null;
//        in.add("user");
//        String selectedTableName = "user";
//        Change change = new Change();
//        assertEquals(selectedTableName, change.isSelectedTableName("sqlcmd"));
//    }
}
