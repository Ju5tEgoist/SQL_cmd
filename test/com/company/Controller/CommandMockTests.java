package com.company.Controller;

/**
 * Created by yulia on 28.02.17.
 */
import com.company.Controller.Command.*;
import com.company.model.DatabaseManager;
import com.company.view.CustomInputStream;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class CommandMockTests {

    private CustomInputStream in;
    private ByteArrayOutputStream out;
    DatabaseManager mock = mock(DatabaseManager.class);

    @Before
    public void setup() throws SQLException, ClassNotFoundException {
        out = new ByteArrayOutputStream();
        in = new CustomInputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));
        in.add("sqlcmd|postgres|yes");
        DatabaseManager.getConnection();
    }

    private void isTrue(boolean value) {
        mock.getStatement();
        verify(mock).getStatement();
        assertTrue(value);
    }

    private void verifyProcess(AbstractCommand spy, String command) {
        try {
            doNothing().when(spy).execute(command);
            spy.shouldProcess(command);
            spy.execute(command);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void shouldProcessClear() throws SQLException, ClassNotFoundException {
        Clear clear = new Clear(mock);
        isTrue(clear.shouldProcess("clear"));
    }
    @Test
    public void shouldClear() throws SQLException, ClassNotFoundException {
        Clear clear = new Clear(mock);
        Clear spy = Mockito.spy(clear);
        String command = "clear";
        verifyProcess(spy, command);
    }


    @Test
    public void shouldProcessConnect() throws SQLException, ClassNotFoundException {
        Connect connect = new Connect(mock);
        isTrue(connect.shouldProcess("connect"));
    }
    @Test
    public void shouldConnect() throws SQLException, ClassNotFoundException {
        Connect connect = new Connect(mock);
        Connect spy = Mockito.spy(connect);
        String command = "connect";
        verifyProcess(spy, command);
    }

    @Test
    public void shouldProcessCreate() throws SQLException, ClassNotFoundException {
        Create create = new Create(mock);
        isTrue(create.shouldProcess("create"));
    }
    @Test
    public void shouldCreate() throws SQLException, ClassNotFoundException {
        Create create = new Create(mock);
        Create spy = Mockito.spy(create);
        String command = "create";
        verifyProcess(spy, command);
    }

    @Test
    public void shouldProcessDelete() throws SQLException, ClassNotFoundException {
        Delete delete = new Delete(mock);
        isTrue(delete.shouldProcess("delete"));
    }
    @Test
    public void shouldDelete() throws SQLException, ClassNotFoundException {
        Delete delete = new Delete(mock);
        Delete spy = Mockito.spy(delete);
        String command = "delete";
        verifyProcess(spy, command);
    }

    @Test
    public void shouldProcessDrop() throws SQLException, ClassNotFoundException {
        Drop drop = new Drop(mock);
        isTrue(drop.shouldProcess("drop"));
    }
    @Test
    public void shouldDrop() throws SQLException, ClassNotFoundException {
        Drop drop = new Drop(mock);
        Drop spy = Mockito.spy(drop);
        String command = "drop";
        verifyProcess(spy, command);
    }

    @Test
    public void shouldProcessFind() throws SQLException, ClassNotFoundException {
        Find find = new Find(mock);
        isTrue(find.shouldProcess("find"));
    }
    @Test
    public void shouldFind() throws SQLException, ClassNotFoundException {
        Find find = new Find(mock);
        Find spy = Mockito.spy(find);
        String command = "find";
        verifyProcess(spy, command);
    }

    @Test
    public void shouldProcessHelp() throws SQLException, ClassNotFoundException {
        Help help = new Help(mock);
        isTrue(help.shouldProcess("help"));
    }
    @Test
    public void shouldHelp() throws SQLException, ClassNotFoundException {
        Help help = new Help(mock);
        Help spy = Mockito.spy(help);
        String command = "help";
        verifyProcess(spy, command);
    }

    @Test
    public void shouldProcessInsert() throws SQLException, ClassNotFoundException {
        Insert insert = new Insert(mock);
        isTrue(insert.shouldProcess("insert"));
    }
    @Test
    public void shouldInsert() throws SQLException, ClassNotFoundException {
        Insert insert = new Insert(mock);
        Insert spy = Mockito.spy(insert);
        String command = "insert";
        verifyProcess(spy, command);
    }

    @Test
    public void shouldProcessList() throws SQLException, ClassNotFoundException {
        List list = new List(mock);
        isTrue(list.shouldProcess("list"));
    }
    @Test
    public void shouldList() throws SQLException, ClassNotFoundException {
        List list = new List(mock);
        List spy = Mockito.spy(list);
        String command = "list";
        verifyProcess(spy, command);
    }

    @Test
    public void shouldProcessUpdate() throws SQLException, ClassNotFoundException {
        Update update = new Update(mock);
        isTrue(update.shouldProcess("update"));
    }
    @Test
    public void shouldUpdate() throws SQLException, ClassNotFoundException {
        Update update = new Update(mock);
        Update spy = Mockito.spy(update);
        String command = "update";
        verifyProcess(spy, command);
    }

    @Test
    public void verifyMainCommand() throws SQLException, ClassNotFoundException {
        MainCommand mainCommand = new MainCommand();
        MainCommand spy = Mockito.spy(mainCommand);
        doNothing().when(spy).perform();
        doNothing().when(spy).getCommand();
    }
}
