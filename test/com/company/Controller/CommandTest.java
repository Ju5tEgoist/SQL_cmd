package com.company.Controller;

        import com.company.Controller.Command.*;
        import com.company.model.DatabaseManager;
        import org.junit.Before;
        import org.junit.Test;
        import java.io.ByteArrayOutputStream;
        import java.io.PrintStream;
        import java.sql.SQLException;
        import static org.junit.Assert.assertTrue;
/**
 * Created by yulia on 01.12.16.
 */
public class CommandTest {
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
    public void checkCommand() throws SQLException, ClassNotFoundException {
        in.add("change 1|1");
        String[] parts = {"change", "1|1"};
        Change change = new Change();
        assertTrue(change.checkCommand("user", parts));
    }

    @Test
    public void shouldSuccessfullyExit()  {
        in.add("exit");
        Exit exit = new Exit();
        assertTrue(exit.shouldProcess("exit"));
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
    @Test
    public void commandListShouldProcess(){
        CommandList commandList = new CommandList();
        assertTrue(commandList.shouldProcess("command list"));
    }


}
