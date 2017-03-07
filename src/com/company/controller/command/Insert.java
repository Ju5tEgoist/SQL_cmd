package com.company.controller.command;

import com.company.controller.query.builder.InsertTableQueryBuilder;
import com.company.controller.query.executor.UpdateSqlQueryExecutor;
import com.company.controller.query.parameter.provider.ClearParametersProvider;
import com.company.model.exception.CommandExecutionException;
import com.company.view.ConsoleManager;
import com.company.view.View;

/**
 * Created by yulia on 21.02.17.
 */
public class Insert extends AbstractCommand {

    public Insert() {
        super(new ClearParametersProvider(), new InsertTableQueryBuilder(), new UpdateSqlQueryExecutor());
    }

    @Override
    public boolean shouldExecute(String command) {
        return "insert".equals(command);
    }

    @Override
    public void execute() throws CommandExecutionException {
        super.execute();
        View view = new ConsoleManager();
        view.write("Your data were added");
    }
}
